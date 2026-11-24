package ua.com.reactive.gr_402_reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.gr_402_reactive.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
