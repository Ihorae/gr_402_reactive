package ua.com.reactive.gr_402_reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("vehicles")
public class Vehicle {
    @Id
    private Long id;
    private String boardNumber;     // бортовий номер
    private VehicleType type;
    private int capacity;           // місткість
    private Long routeId;           // маршрут, на який призначено
}
