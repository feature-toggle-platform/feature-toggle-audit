package pl.feature.toggle.service.audit.infrastructure.in.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.feature.toggle.service.audit.AbstractUnitTest;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.contracts.shared.EventProcessor;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated.featureToggleCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted.featureToggleDeletedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated.featureToggleUpdatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated.environmentCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.projects.ProjectCreated.projectCreatedEventBuilder;

class KafkaEventConsumerTest extends AbstractUnitTest {

    private KafkaEventConsumer sut;
    private EventProcessor eventProcessor;

    @BeforeEach
    void setUp() {
        var environmentAuditUseCase = mock(EnvironmentAuditUseCase.class);
        var projectAuditUseCase = mock(ProjectAuditUseCase.class);
        var featureToggleAuditUseCase = mock(FeatureToggleAuditUseCase.class);
        eventProcessor = mock(IdempotentEventProcessor.class);
        sut = new KafkaEventConsumer(eventProcessor, environmentAuditUseCase, projectAuditUseCase, featureToggleAuditUseCase);
    }

    @Test
    @DisplayName("Should handle project created event")
    void test01() {
        // given
        var projectId = UUID.randomUUID();
        var projectCreated = projectCreatedEventBuilder()
                .projectId(projectId)
                .build();

        // when
        sut.handle(projectCreated, acknowledgment);

        // then
        verify(eventProcessor).process(eq(projectCreated), any(), any());
    }

    @Test
    @DisplayName("Should handle environmentCreated event")
    void test02() {
        // given
        var environmentId = UUID.randomUUID();
        var environmentCreated = environmentCreatedEventBuilder()
                .projectId(UUID.randomUUID())
                .environmentId(environmentId)
                .build();

        // when
        sut.handle(environmentCreated, acknowledgment);

        // then
        verify(eventProcessor).process(eq(environmentCreated), any(), any());
    }

    @Test
    @DisplayName("Should handle feature toggle created event")
    void test03() {
        // given
        var event = featureToggleCreatedEventBuilder()
                .build();

        // when
        sut.handle(event, acknowledgment);

        // then
        verify(eventProcessor).process(eq(event), any(), any());
    }

    @Test
    @DisplayName("Should handle feature toggle deleted event")
    void test04() {
        // given
        var event = featureToggleDeletedEventBuilder()
                .build();

        // when
        sut.handle(event, acknowledgment);

        // then
        verify(eventProcessor).process(eq(event), any(), any());
    }

    @Test
    @DisplayName("Should handle feature toggle updated event")
    void test05() {
        // given
        var event = featureToggleUpdatedEventBuilder()
                .build();

        // when
        sut.handle(event, acknowledgment);

        // then
        verify(eventProcessor).process(eq(event), any(), any());
    }

}