package pl.feature.toggle.service.audit.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.feature.toggle.service.audit.AbstractUnitTest;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.domain.*;
import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated.environmentCreatedEventBuilder;

class EnvironmentAuditHandlerTest extends AbstractUnitTest {

    private EnvironmentAuditUseCase sut;

    @BeforeEach
    void setUp() {
        sut = new EnvironmentAuditHandler(auditRepository);
    }

    @Test
    @DisplayName("Should handle environment created event and save AuditEntry to repository")
    void test01() {
        // given
        var event = environmentCreatedEventBuilder()
                .environmentId(ENVIRONMENT_ID)
                .projectId(PROJECT_ID)
                .environmentName("TEST")
                .metadata(METADATA)
                .build();

        // when
        sut.handle(event);

        // then
        var auditEntry = auditRepository.getByAuditTarget(AuditTarget.build(TargetType.ENVIRONMENT, event.environmentId()));
        assertThat(auditEntry).isNotNull();
        assertThat(auditEntry.actor()).isEqualTo(AuditActor.build(ACTOR_ID, USERNAME));
        assertThat(auditEntry.changes()).isEqualTo(AuditChanges.empty());
        assertThat(auditEntry.type()).isEqualTo(AuditType.ENVIRONMENT_CREATED);
        assertThat(auditEntry.context()).isEqualTo(AuditContext.forEnvironment(PROJECT_ID, ENVIRONMENT_ID, CORRELATION_ID));
        assertThat(auditEntry.target()).isEqualTo(AuditTarget.build(TargetType.ENVIRONMENT, ENVIRONMENT_ID));
        assertThat(auditEntry.occurredAt()).isEqualTo(METADATA.occurredAt());
        assertThat(auditEntry.id()).isNotNull();
    }

}
