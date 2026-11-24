package ua.com.reactive.gr_402_reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ua.com.reactive.gr_402_reactive.entity.Vehicle;

public interface VehicleRepository extends ReactiveCrudRepository<Vehicle, Long> {

    // транспорт, призначений на маршрут
    Flux<Vehicle> findByRouteId(Long routeId);
}
