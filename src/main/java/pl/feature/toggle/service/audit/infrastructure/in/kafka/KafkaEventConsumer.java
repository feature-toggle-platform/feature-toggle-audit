package pl.feature.toggle.service.audit.infrastructure.in.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@AllArgsConstructor
@KafkaListener(topics = "project-env-events")
class KafkaEventConsumer {
}
