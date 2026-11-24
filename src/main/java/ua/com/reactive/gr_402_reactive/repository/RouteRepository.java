package ua.com.reactive.gr_402_reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.gr_402_reactive.entity.Route;

public interface RouteRepository extends ReactiveCrudRepository<Route, Long> {
}
