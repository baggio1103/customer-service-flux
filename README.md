# Demo Project using Spring Web Flux 
### Reference the following link to find out more about Web Flux

* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#web.reactive)

### Intro

A demo web project that highlights the difference
between traditional synchronous REST APIs and Functional
Non-blocking Asynchronous web service using Spring Web Flux.

### Functionality

A traditional MVC pattern - [Controller - Service - Repository]()

CustomerController Endpoints:

* **_/customers_** - returns a list of customers. It is a
  synchronous blocking endpoint
* **_/customers/stream_** - returns a list of customers. It is an
  asynchronous non-blocking endpoint.

A similar functionality pattern in Web Flux - [Router - Handler - Repository]()

Router provides similar functionality as RestController,
it defines endpoints and method that is responsible for handling request.

```
@Bean
public RouterFunction<ServerResponse> routerFunction() {
return RouterFunctions.route()
.GET("/router/customers", customerHandler::loadCustomers)
.build();
}
```
