package com.javajedi.repository;

import com.javajedi.data.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class CustomerRepository {

    public List<Customer> customerList() {
        var names = List.of("james", "mike", "baggio", "dave", "david");
        return IntStream.rangeClosed(1, 10)
                .peek(i -> log.info("""
                        Customer with id: %s has been processed
                        """.formatted(i))
                )
                .peek(this::sleep)
                .mapToObj(i -> new Customer(i, names.get(i % names.size()) + " " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> customerStream() {
        var names = List.of("james", "mike", "baggio", "dave", "david");
         return Flux.range(1, 10)
                 .doOnEach(i -> log.info("""
                        %s : Customer with id: %s has been processed
                        """.formatted(Thread.currentThread().getName(), i)))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new Customer(i, names.get(i % names.size()) + " " + i));
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
