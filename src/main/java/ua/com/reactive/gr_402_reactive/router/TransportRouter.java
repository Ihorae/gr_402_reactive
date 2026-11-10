package ua.com.reactive.gr_402_reactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ua.com.reactive.gr_402_reactive.handler.TransportHandler;

@Configuration(proxyBeanMethods = false)
public class TransportRouter {

    @Bean
    public RouterFunction<ServerResponse> transportRoute(TransportHandler handler) {
        return RouterFunctions.route()
                .GET("/routes", handler::getRoutes)
                .GET("/routes/{id}/vehicles", handler::getVehiclesByRoute)
                .GET("/routes/{id}/schedule", handler::getSchedule)
                .GET("/vehicles", handler::getVehicles)
                .build();
    }
}