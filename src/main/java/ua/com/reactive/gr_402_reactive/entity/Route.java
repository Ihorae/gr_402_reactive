package ua.com.reactive.gr_402_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Long id;
    private String number;
    private String name;
    private VehicleType type;
    private int intervalMinutes;
    private LocalTime firstDeparture;
    private LocalTime lastDeparture;
}