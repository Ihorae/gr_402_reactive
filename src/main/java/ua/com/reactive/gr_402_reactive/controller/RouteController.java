package ua.com.reactive.gr_402_reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.Route;
import ua.com.reactive.gr_402_reactive.service.RouteService;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public Flux<Route> list() {
        return routeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Route> get(@PathVariable Long id) {
        return routeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Route> create(@RequestBody Route route) {
        return routeService.create(route);
    }

    @PutMapping("/{id}")
    public Mono<Route> update(@PathVariable Long id, @RequestBody Route route) {
        return routeService.update(id, route);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return routeService.delete(id);
    }
}
