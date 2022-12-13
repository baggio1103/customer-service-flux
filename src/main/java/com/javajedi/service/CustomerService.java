package com.javajedi.service;

import com.javajedi.data.Customer;
import com.javajedi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> loadAllCustomers() {
        var start = System.currentTimeMillis();
        var customers = customerRepository.customerList();
        var end = System.currentTimeMillis();
        log.info("Execution time: " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream() {
        var start = System.currentTimeMillis();
        var customers = customerRepository.customerStream();
        var end = System.currentTimeMillis();
        log.info("Execution time: " + (end - start));
        System.out.println(Thread.currentThread().getName());
        return customers;
    }

}
