package ua.com.reactive.gr_402_reactive.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
public class TransportHandler {

    private final List<Route> routes = List.of(
            new Route(1L, "18", "Вокзал - Лівобережна", VehicleType.BUS, 10, LocalTime.of(6, 0), LocalTime.of(22, 0)),
            new Route(2L, "Т-5", "Центр - Оболонь", VehicleType.TROLLEYBUS, 12, LocalTime.of(6, 30), LocalTime.of(23, 0)),
            new Route(3L, "12", "Подол - Борщагівка", VehicleType.TRAM, 15, LocalTime.of(5, 30), LocalTime.of(23, 30))
    );

    private final List<Vehicle> vehicles = List.of(
            new Vehicle(1L, "AA-101", VehicleType.BUS, 80, 1L),
            new Vehicle(2L, "AA-102", VehicleType.BUS, 80, 1L),
            new Vehicle(3L, "TR-201", VehicleType.TROLLEYBUS, 100, 2L),
            new Vehicle(4L, "TM-301", VehicleType.TRAM, 150, 3L),
            new Vehicle(5L, "TM-302", VehicleType.TRAM, 150, 3L)
    );

    public Mono<ServerResponse> getRoutes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(routes), Route.class);
    }

    public Mono<ServerResponse> getVehicles(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(vehicles), Vehicle.class);
    }

    // транспорт, призначений на маршрут: GET /routes/1/vehicles
    public Mono<ServerResponse> getVehiclesByRoute(ServerRequest request) {
        long routeId = Long.parseLong(request.pathVariable("id"));
        Flux<Vehicle> result = Flux.fromIterable(vehicles)
                .filter(v -> v.getRouteId() == routeId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, Vehicle.class);
    }

    // розклад за інтервалом: GET /routes/1/schedule
    public Mono<ServerResponse> getSchedule(ServerRequest request) {
        long routeId = Long.parseLong(request.pathVariable("id"));

        Flux<ScheduleEntry> schedule = Flux.fromIterable(routes)
                .filter(r -> r.getId() == routeId)
                .flatMap(r -> {
                    long minutes = Duration.between(r.getFirstDeparture(), r.getLastDeparture()).toMinutes();
                    long count = minutes / r.getIntervalMinutes() + 1;
                    return Flux.range(0, (int) count)
                            .map(i -> new ScheduleEntry(
                                    r.getId(),
                                    r.getFirstDeparture().plusMinutes((long) i * r.getIntervalMinutes())));
                });

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(schedule, ScheduleEntry.class);
    }
}