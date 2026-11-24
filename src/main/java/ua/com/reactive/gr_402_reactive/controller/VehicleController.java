package ua.com.reactive.gr_402_reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.Vehicle;
import ua.com.reactive.gr_402_reactive.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    // GET /api/vehicles або GET /api/vehicles?routeId=1
    @GetMapping
    public Flux<Vehicle> list(@RequestParam(required = false) Long routeId) {
        return routeId == null
                ? vehicleService.findAll()
                : vehicleService.findByRoute(routeId);
    }

    @GetMapping("/{id}")
    public Mono<Vehicle> get(@PathVariable Long id) {
        return vehicleService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vehicle> create(@RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    @PutMapping("/{id}")
    public Mono<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.update(id, vehicle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return vehicleService.delete(id);
    }
}
