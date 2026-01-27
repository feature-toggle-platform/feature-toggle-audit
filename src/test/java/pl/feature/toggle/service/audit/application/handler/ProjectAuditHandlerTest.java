package pl.feature.toggle.service.audit.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.feature.toggle.service.audit.AbstractUnitTest;
import pl.feature.toggle.service.audit.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.feature.toggle.service.contracts.event.projects.ProjectCreated.projectCreatedEventBuilder;

class ProjectAuditHandlerTest extends AbstractUnitTest {

    private ProjectAuditHandler sut;

    @BeforeEach
    void setUp() {
        sut = new ProjectAuditHandler(auditRepository);
    }

    @Test
    @DisplayName("Should handle project created event and save AuditEntry to repository")
    void test01() {
        // given
        var event = projectCreatedEventBuilder()
                .projectName("TEST")
                .projectId(PROJECT_ID)
                .metadata(METADATA)
                .build();

        // when
        sut.handle(event);

        // then
        var auditEntry = auditRepository.getByAuditTarget(AuditTarget.build(TargetType.PROJECT, event.projectId()));
        assertThat(auditEntry).isNotNull();
        assertThat(auditEntry.actor()).isEqualTo(AuditActor.build(ACTOR_ID, USERNAME));
        assertThat(auditEntry.changes()).isEqualTo(AuditChanges.empty());
        assertThat(auditEntry.type()).isEqualTo(AuditType.PROJECT_CREATED);
        assertThat(auditEntry.context()).isEqualTo(AuditContext.forProject(PROJECT_ID, CORRELATION_ID));
        assertThat(auditEntry.target()).isEqualTo(AuditTarget.build(TargetType.PROJECT, PROJECT_ID));
        assertThat(auditEntry.occurredAt()).isEqualTo(METADATA.occurredAt());
        assertThat(auditEntry.id()).isNotNull();
    }

}
