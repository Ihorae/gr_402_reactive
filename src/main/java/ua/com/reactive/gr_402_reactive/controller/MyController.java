package ua.com.reactive.gr_402_reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.com.reactive.gr_402_reactive.entity.Client;

@RestController
public class MyController {

    @GetMapping("/clients")
    public Flux<Client> getClients() {
        return Flux.just(
                        new Client(1L, "Ihor", "Miroshnikov", 18),
                        new Client(2L, "Iva", "Miroshnikova", 19),
                        new Client(3L, "Inna", "Miroshnikova", 20))
                .skip(0)
                .take(2);
    }
}