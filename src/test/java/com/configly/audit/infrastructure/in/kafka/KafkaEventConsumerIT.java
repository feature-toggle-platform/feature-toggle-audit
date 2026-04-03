package com.configly.audit.infrastructure.in.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.configly.audit.AbstractITTest;
import com.configly.audit.FakeAcknowledgment;
import com.configly.audit.application.port.in.AuditUseCase;
import com.configly.event.processing.api.EventProcessor;
import com.configly.model.Revision;
import com.configly.model.environment.EnvironmentId;
import com.configly.model.environment.EnvironmentStatus;
import com.configly.model.project.ProjectId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.doThrow;
import static com.configly.contracts.event.environment.EnvironmentCreated.environmentCreatedEventBuilder;

class KafkaEventConsumerIT extends AbstractITTest {

    @Autowired
    private EventProcessor eventProcessor;
    @MockitoBean
    private AuditUseCase auditUseCase;

    private FakeAcknowledgment fakeAcknowledgment;
    private KafkaEventConsumer sut;

    @BeforeEach
    void setUp() {
        fakeAcknowledgment = new FakeAcknowledgment();
        sut = new KafkaEventConsumer(eventProcessor, auditUseCase);
    }

    @Test
    void should_not_mark_event_as_processed_when_projection_handling_fails() {
        // given
        var projectId = ProjectId.create();
        var envId = EnvironmentId.create();
        var event = environmentCreatedEventBuilder()
                .projectId(projectId.uuid())
                .environmentId(envId.uuid())
                .environmentName("test")
                .status(EnvironmentStatus.ACTIVE.name())
                .revision(Revision.initialRevision().value())
                .build();

        doThrow(new RuntimeException("Something went wrong"))
                .when(auditUseCase)
                .handle(event);

        // when
        var exception = catchException(() -> sut.handle(event, fakeAcknowledgment));

        // then
        assertThat(exception).isNotNull();
        var processedEvents = countProcessedEvents();
        assertThat(processedEvents).isZero();
    }

}