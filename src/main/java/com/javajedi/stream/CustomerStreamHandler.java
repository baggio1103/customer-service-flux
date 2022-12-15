package com.javajedi.stream;

import com.javajedi.data.Customer;
import com.javajedi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerStreamHandler {

    private final CustomerRepository customerRepository;

    public Mono<ServerResponse> loadCustomerStream(ServerRequest serverRequest) {
        var customerFlux = customerRepository.loadCustomerStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux, Customer.class);
    }

}
