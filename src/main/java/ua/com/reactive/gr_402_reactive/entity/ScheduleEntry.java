package ua.com.reactive.gr_402_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("schedule_entries")
public class ScheduleEntry {
    @Id
    private Long id;
    private Long routeId;
    private LocalTime departureTime;
}
