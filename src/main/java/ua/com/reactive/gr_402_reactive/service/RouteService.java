package ua.com.reactive.gr_402_reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.Route;
import ua.com.reactive.gr_402_reactive.repository.RouteRepository;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public Flux<Route> findAll() {
        return routeRepository.findAll();
    }

    public Mono<Route> findById(Long id) {
        return routeRepository.findById(id)
                .switchIfEmpty(notFound(id));
    }

    public Mono<Route> create(Route route) {
        route.setId(null);                       // id генерує БД
        return routeRepository.save(route);
    }

    public Mono<Route> update(Long id, Route route) {
        return findById(id)
                .flatMap(existing -> {
                    route.setId(id);
                    return routeRepository.save(route);
                });
    }

    public Mono<Void> delete(Long id) {
        return findById(id)
                .flatMap(routeRepository::delete);
    }

    private Mono<Route> notFound(Long id) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Маршрут не знайдено: " + id));
    }
}
