package com.configly.audit.infrastructure.in.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import com.configly.audit.application.port.in.AuditUseCase;
import com.configly.contracts.event.environment.EnvironmentCreated;
import com.configly.contracts.event.environment.EnvironmentStatusChanged;
import com.configly.contracts.event.environment.EnvironmentTypeChanged;
import com.configly.contracts.event.environment.EnvironmentUpdated;
import com.configly.contracts.event.featuretoggle.FeatureToggleCreated;
import com.configly.contracts.event.featuretoggle.FeatureToggleStatusChanged;
import com.configly.contracts.event.featuretoggle.FeatureToggleUpdated;
import com.configly.contracts.event.featuretoggle.FeatureToggleValueChanged;
import com.configly.contracts.event.project.ProjectCreated;
import com.configly.contracts.event.project.ProjectStatusChanged;
import com.configly.contracts.event.project.ProjectUpdated;
import com.configly.event.processing.api.EventProcessor;

@Slf4j
@AllArgsConstructor
@KafkaListener(topics = {"${topics.configly-structure-events}", "${topics.configly-toggle-events}"})
class KafkaEventConsumer {

    private final EventProcessor eventProcessor;
    private final AuditUseCase auditUseCase;

    @KafkaHandler
    void handle(FeatureToggleCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(FeatureToggleUpdated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(FeatureToggleStatusChanged event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(FeatureToggleValueChanged event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(ProjectCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(ProjectStatusChanged event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }


    @KafkaHandler
    void handle(ProjectUpdated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(EnvironmentCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(EnvironmentUpdated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(EnvironmentStatusChanged event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(EnvironmentTypeChanged event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                auditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

}
