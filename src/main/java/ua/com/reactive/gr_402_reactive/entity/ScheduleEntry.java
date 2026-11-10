package ua.com.reactive.gr_402_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntry {
    private Long routeId;
    private LocalTime departureTime;
}