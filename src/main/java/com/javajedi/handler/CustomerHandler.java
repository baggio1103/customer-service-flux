package com.javajedi.handler;

import com.javajedi.data.Customer;
import com.javajedi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerRepository customerRepository;

   public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest) {
       return ServerResponse.ok().body(customerRepository.customerStream(), Customer.class);
   }

    public Mono<ServerResponse> loadCustomerById(ServerRequest serverRequest) {
       var id = Integer.valueOf(serverRequest.pathVariable("input"));
       var customerById = customerRepository.customerStream()
               .filter(customer -> customer.id().equals(id))
               .next();
        return ServerResponse.ok().body(customerById, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        var requestBody = serverRequest.bodyToMono(Customer.class);
        var customerMono = requestBody.map(customer -> """
                Id: %s, name: %s
                """.formatted(customer.id(), customer.name()));
        return ServerResponse.ok().body(customerMono, String.class);
    }

}
