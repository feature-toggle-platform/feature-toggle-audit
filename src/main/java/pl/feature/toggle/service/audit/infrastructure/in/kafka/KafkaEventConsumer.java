package pl.feature.toggle.service.audit.infrastructure.in.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentCreated;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentStatusChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentTypeChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleStatusChanged;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleValueChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectCreated;
import pl.feature.toggle.service.contracts.event.project.ProjectStatusChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectUpdated;
import pl.feature.toggle.service.event.processing.api.EventProcessor;

@Slf4j
@AllArgsConstructor
@KafkaListener(topics = {"${topics.feature-toggle-configuration-events}", "${topics.feature-toggle-events}"})
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
