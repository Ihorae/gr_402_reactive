package ua.com.reactive.gr_402_reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.gr_402_reactive.entity.ScheduleEntry;
import ua.com.reactive.gr_402_reactive.service.ScheduleEntryService;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleEntryController {

    private final ScheduleEntryService scheduleEntryService;

    // GET /api/schedule або GET /api/schedule?routeId=1
    @GetMapping
    public Flux<ScheduleEntry> list(@RequestParam(required = false) Long routeId) {
        return routeId == null
                ? scheduleEntryService.findAll()
                : scheduleEntryService.findByRoute(routeId);
    }

    @GetMapping("/{id}")
    public Mono<ScheduleEntry> get(@PathVariable Long id) {
        return scheduleEntryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ScheduleEntry> create(@RequestBody ScheduleEntry entry) {
        return scheduleEntryService.create(entry);
    }

    @PutMapping("/{id}")
    public Mono<ScheduleEntry> update(@PathVariable Long id, @RequestBody ScheduleEntry entry) {
        return scheduleEntryService.update(id, entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return scheduleEntryService.delete(id);
    }
}
