package pl.feature.toggle.service.audit.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.feature.toggle.service.audit.AbstractUnitTest;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.audit.domain.*;
import pl.feature.toggle.service.contracts.shared.Changes;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.feature.toggle.service.audit.domain.AuditChanges.build;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated.featureToggleCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted.featureToggleDeletedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated.featureToggleUpdatedEventBuilder;
import static pl.feature.toggle.service.contracts.shared.Changes.buildChange;

class FeatureToggleAuditHandlerTest extends AbstractUnitTest {

    private AuditUseCase sut;

    @BeforeEach
    void setUp() {
        sut = new AuditUseCaseHandler(auditRepository);
    }

    @Test
    @DisplayName("Should handle feature toggle created event and save AuditEntry to repository")
    void test01() {
        // given
        var event = featureToggleCreatedEventBuilder()
                .id(FEATURE_TOGGLE_ID)
                .projectId(PROJECT_ID)
                .environmentId(ENVIRONMENT_ID)
                .metadata(METADATA)
                .name("TEST")
                .description("TEST")
                .value("TRUE")
                .type("BOOLEAN")
                .createdAt(LocalDateTime.now())
                .build();

        // when
        sut.handle(event);

        // then
        var auditEntry = auditRepository.getByAuditTarget(AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id()));
        assertThat(auditEntry).isNotNull();
        assertThat(auditEntry.actor()).isEqualTo(AuditActor.build(ACTOR_ID, USERNAME));
        assertThat(auditEntry.changes()).isEqualTo(AuditChanges.empty());
        assertThat(auditEntry.type()).isEqualTo(AuditType.FEATURE_TOGGLE_CREATED);
        assertThat(auditEntry.context()).isEqualTo(AuditContext.forFeatureToggle(PROJECT_ID, ENVIRONMENT_ID, CORRELATION_ID));
        assertThat(auditEntry.target()).isEqualTo(AuditTarget.build(TargetType.FEATURE_TOGGLE, FEATURE_TOGGLE_ID));
        assertThat(auditEntry.time()).isEqualTo(AuditTime.from(METADATA.occurredAt()));
        assertThat(auditEntry.id()).isNotNull();
    }

    @Test
    @DisplayName("Should handle feature toggle updated event and save AuditEntry to repository")
    void test02() {
        // given
        var event = featureToggleUpdatedEventBuilder()
                .metadata(METADATA)
                .type("BOOLEAN")
                .createdAt(LocalDateTime.now())
                .description("TEST")
                .value("TRUE")
                .id(FEATURE_TOGGLE_ID)
                .projectId(PROJECT_ID)
                .environmentId(ENVIRONMENT_ID)
                .name("TEST")
                .changes(Changes.of(buildChange("value", "FALSE", "TRUE")))
                .build();

        // when
        sut.handle(event);

        // then
        var auditEntry = auditRepository.getByAuditTarget(AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id()));
        assertThat(auditEntry).isNotNull();
        assertThat(auditEntry.actor()).isEqualTo(AuditActor.build(ACTOR_ID, USERNAME));
        assertThat(auditEntry.changes()).isEqualTo(AuditChanges.of(List.of(build("value", "FALSE", "TRUE"))));
        assertThat(auditEntry.type()).isEqualTo(AuditType.FEATURE_TOGGLE_UPDATED);
        assertThat(auditEntry.context()).isEqualTo(AuditContext.forFeatureToggle(PROJECT_ID, ENVIRONMENT_ID, CORRELATION_ID));
        assertThat(auditEntry.target()).isEqualTo(AuditTarget.build(TargetType.FEATURE_TOGGLE, FEATURE_TOGGLE_ID));
        assertThat(auditEntry.time()).isEqualTo(AuditTime.from(METADATA.occurredAt()));
        assertThat(auditEntry.id()).isNotNull();
    }

    @Test
    @DisplayName("Should handle feature toggle deleted event and save AuditEntry to repository")
    void test03() {
        // given
        var event = featureToggleDeletedEventBuilder()
                .environmentId(ENVIRONMENT_ID)
                .projectId(PROJECT_ID)
                .id(FEATURE_TOGGLE_ID)
                .metadata(METADATA)
                .build();

        // when
        sut.handle(event);

        // then
        var auditEntry = auditRepository.getByAuditTarget(AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id()));
        assertThat(auditEntry).isNotNull();
        assertThat(auditEntry.actor()).isEqualTo(AuditActor.build(ACTOR_ID, USERNAME));
        assertThat(auditEntry.changes()).isEqualTo(AuditChanges.empty());
        assertThat(auditEntry.type()).isEqualTo(AuditType.FEATURE_TOGGLE_DELETED);
        assertThat(auditEntry.context()).isEqualTo(AuditContext.forFeatureToggle(PROJECT_ID, ENVIRONMENT_ID, CORRELATION_ID));
        assertThat(auditEntry.target()).isEqualTo(AuditTarget.build(TargetType.FEATURE_TOGGLE, FEATURE_TOGGLE_ID));
        assertThat(auditEntry.time()).isEqualTo(AuditTime.from(METADATA.occurredAt()));
        assertThat(auditEntry.id()).isNotNull();
    }

}
