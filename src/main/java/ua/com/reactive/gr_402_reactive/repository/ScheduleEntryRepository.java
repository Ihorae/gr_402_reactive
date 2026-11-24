package ua.com.reactive.gr_402_reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ua.com.reactive.gr_402_reactive.entity.ScheduleEntry;

public interface ScheduleEntryRepository extends ReactiveCrudRepository<ScheduleEntry, Long> {

    // розклад маршруту за часом відправлення
    Flux<ScheduleEntry> findByRouteIdOrderByDepartureTime(Long routeId);
}
