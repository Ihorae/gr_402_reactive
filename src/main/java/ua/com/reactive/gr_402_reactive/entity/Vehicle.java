package ua.com.reactive.gr_402_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String boardNumber;
    private VehicleType type;
    private int capacity;
    private Long routeId;
}