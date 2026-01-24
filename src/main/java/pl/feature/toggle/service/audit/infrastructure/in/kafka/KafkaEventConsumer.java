package pl.feature.toggle.service.audit.infrastructure.in.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.ProcessedEventRepository;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;
import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;
import pl.feature.toggle.service.contracts.event.projects.ProjectCreated;
import pl.feature.toggle.service.contracts.shared.EventProcessor;

@Slf4j
@AllArgsConstructor
@KafkaListener(topics = {"${topics.project-env-events}", "${topics.feature-toggle-events}"})
class KafkaEventConsumer {

    private final EventProcessor eventProcessor;
    private final EnvironmentAuditUseCase environmentAuditUseCase;
    private final ProjectAuditUseCase projectAuditUseCase;
    private final FeatureToggleAuditUseCase featureToggleAuditUseCase;

    @KafkaHandler
    void handle(FeatureToggleCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                featureToggleAuditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(FeatureToggleUpdated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                featureToggleAuditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(FeatureToggleDeleted event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                featureToggleAuditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(ProjectCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                projectAuditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

    @KafkaHandler
    void handle(EnvironmentCreated event, Acknowledgment acknowledgment) {
        eventProcessor.process(
                event,
                environmentAuditUseCase::handle,
                acknowledgment::acknowledge
        );
    }

}
