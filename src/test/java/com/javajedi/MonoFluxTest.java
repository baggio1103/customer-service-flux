package com.javajedi;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    void testMono() {
        var mono = Mono.just("Java-jedi")
                .then(Mono.error(new RuntimeException("Error in Flux")))
                        .log();
        mono.subscribe(System.out::println, e -> System.out.println(e.getMessage()), () -> System.out.println("Success"));
    }

    @Test
    void testFlux() {
        var flux = Flux.just("Java", "Spring", "PostgreSQL")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception happened in Flux")))
                .concatWithValues("Cloud")
                .log();
        flux.subscribe(value -> System.out.println(value + " " + Thread.currentThread().getName()), e -> System.out.println(e.getMessage()));
        System.out.println(Thread.currentThread().getName());
    }

}
