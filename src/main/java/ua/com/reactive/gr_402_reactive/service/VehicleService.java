package ua.com.reactive.gr_402_reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.Vehicle;
import ua.com.reactive.gr_402_reactive.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Flux<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Flux<Vehicle> findByRoute(Long routeId) {
        return vehicleRepository.findByRouteId(routeId);
    }

    public Mono<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id)
                .switchIfEmpty(notFound(id));
    }

    public Mono<Vehicle> create(Vehicle vehicle) {
        vehicle.setId(null);
        return vehicleRepository.save(vehicle);
    }

    public Mono<Vehicle> update(Long id, Vehicle vehicle) {
        return findById(id)
                .flatMap(existing -> {
                    vehicle.setId(id);
                    return vehicleRepository.save(vehicle);
                });
    }

    public Mono<Void> delete(Long id) {
        return findById(id)
                .flatMap(vehicleRepository::delete);
    }

    private Mono<Vehicle> notFound(Long id) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Транспортний засіб не знайдено: " + id));
    }
}
