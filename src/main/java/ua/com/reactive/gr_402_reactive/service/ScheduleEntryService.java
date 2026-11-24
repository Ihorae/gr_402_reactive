package ua.com.reactive.gr_402_reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.ScheduleEntry;
import ua.com.reactive.gr_402_reactive.repository.ScheduleEntryRepository;

@Service
@RequiredArgsConstructor
public class ScheduleEntryService {

    private final ScheduleEntryRepository scheduleEntryRepository;

    public Flux<ScheduleEntry> findAll() {
        return scheduleEntryRepository.findAll();
    }

    public Flux<ScheduleEntry> findByRoute(Long routeId) {
        return scheduleEntryRepository.findByRouteIdOrderByDepartureTime(routeId);
    }

    public Mono<ScheduleEntry> findById(Long id) {
        return scheduleEntryRepository.findById(id)
                .switchIfEmpty(notFound(id));
    }

    public Mono<ScheduleEntry> create(ScheduleEntry entry) {
        entry.setId(null);
        return scheduleEntryRepository.save(entry);
    }

    public Mono<ScheduleEntry> update(Long id, ScheduleEntry entry) {
        return findById(id)
                .flatMap(existing -> {
                    entry.setId(id);
                    return scheduleEntryRepository.save(entry);
                });
    }

    public Mono<Void> delete(Long id) {
        return findById(id)
                .flatMap(scheduleEntryRepository::delete);
    }

    private Mono<ScheduleEntry> notFound(Long id) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Запис розкладу не знайдено: " + id));
    }
}
