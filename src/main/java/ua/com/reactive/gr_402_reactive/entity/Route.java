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
@Table("routes")
public class Route {
    @Id
    private Long id;
    private String number;          // номер маршруту
    private String name;            // назва маршруту
    private VehicleType type;       // тип транспорту
    private int intervalMinutes;    // інтервал руху, хв
    private LocalTime firstDeparture;
    private LocalTime lastDeparture;
}
