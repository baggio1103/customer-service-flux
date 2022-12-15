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

    private static final List<String> NAMES = List.of("james", "mike", "baggio", "dave", "david");

    public List<Customer> customerList() {
        return IntStream.rangeClosed(1, 100)
                .peek(i -> log.info("""
                        %s : Customer with id: %s has been processed
                        """.formatted(Thread.currentThread().getName(), i))
                )
                .peek(this::sleep)
                .mapToObj(i -> new Customer(i, NAMES.get(i % NAMES.size()) + " " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> loadCustomerStream() {
         return Flux.range(1, 10)
                 .doOnEach(i -> log.info("""
                        %s : Customer with id: %s has been processed
                        """.formatted(Thread.currentThread().getName(), i)))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new Customer(i, NAMES.get(i % NAMES.size()) + " " + i));
    }

    public Flux<Customer> customerStream() {
        return Flux.range(1, 100)
                .doOnEach(i -> log.info("""
                        %s : Customer with id: %s has been processed
                        """.formatted(Thread.currentThread().getName(), i)))
                .delayElements(Duration.ofMillis(100))
                .map(i -> new Customer(i, NAMES.get(i % NAMES.size()) + " " + i));
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
