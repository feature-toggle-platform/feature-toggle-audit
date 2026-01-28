package pl.feature.toggle.service.audit.application.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.feature.toggle.service.audit.domain.AuditChanges.AuditChange;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditTime;
import pl.feature.toggle.service.audit.domain.AuditType;
import pl.feature.toggle.service.audit.domain.TargetType;
import pl.feature.toggle.service.contracts.shared.Changes;
import pl.feature.toggle.service.contracts.shared.Metadata;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated.featureToggleCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted.featureToggleDeletedEventBuilder;
import static pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated.featureToggleUpdatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated.environmentCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.event.projects.ProjectCreated.projectCreatedEventBuilder;
import static pl.feature.toggle.service.contracts.shared.Changes.buildChange;

class MapperTest {

    @Test
    void should_map_changes_for_feature_toggle_updated() {
        var actorId = UUID.randomUUID();
        var username = "admin";
        var correlationId = "111111";
        var time = AuditTime.from(LocalDateTime.now());

        var projectId = UUID.randomUUID();
        var environmentId = UUID.randomUUID();
        var featureToggleId = UUID.randomUUID();

        var event = featureToggleUpdatedEventBuilder()
                .id(featureToggleId)
                .projectId(projectId)
                .environmentId(environmentId)
                .createdAt(time.occurredAt())
                .updatedAt(time.occurredAt())
                .type("BOOLEAN")
                .value("true")
                .name("TEST")
                .description("description")
                .metadata(Metadata.create(actorId.toString(), username, correlationId))
                .changes(Changes.of(buildChange(
                        "value", "false", "true"
                )))
                .build();

        // when
        var audit = Mapper.buildAuditFor(event);

        // then
        assertThat(audit.type()).isEqualTo(AuditType.FEATURE_TOGGLE_UPDATED);
        assertThat(audit.target().type()).isEqualTo(TargetType.FEATURE_TOGGLE);
        assertThat(audit.target().targetId()).isEqualTo(featureToggleId);

        assertThat(audit.changes().changes())
                .containsExactly(new AuditChange("value", "false", "true"));
    }

    @ParameterizedTest
    @MethodSource("events_without_changes")
    void should_map_common_fields_for_events_without_changes(Case c) {
        // when
        var audit = c.mapperCall().get();

        // then
        assertThat(audit.type()).isEqualTo(c.expectedAuditType);

        assertThat(audit.target().type()).isEqualTo(c.expectedTargetType);
        assertThat(audit.target().targetId()).isEqualTo(c.expectedTargetId);

        assertThat(audit.actor().actorId()).isEqualTo(c.actorId.toString());
        assertThat(audit.actor().username()).isEqualTo(c.username);

        assertThat(audit.context().projectId()).isEqualTo(c.projectId);
        assertThat(audit.context().environmentId()).isEqualTo(c.environmentId);
        assertThat(audit.context().correlationId()).isEqualTo(c.correlationId);

        assertThat(audit.time()).isEqualTo(c.time);

        assertThat(audit.changes().changes()).isEmpty();
    }

    private static Stream<Case> events_without_changes() {
        var actorId = UUID.randomUUID();
        var username = "admin";
        var correlationId = "111111";
        var occurredAt = AuditTime.from(LocalDateTime.now());

        var projectId = UUID.randomUUID();
        var environmentId = UUID.randomUUID();
        var featureToggleId = UUID.randomUUID();

        var metadata = Metadata.create(actorId.toString(), username, correlationId);

        return Stream.of(
                Case.environmentCreated(actorId, username, correlationId, occurredAt, projectId, environmentId, metadata),
                Case.projectCreated(actorId, username, correlationId, occurredAt, projectId, metadata),
                Case.featureToggleCreated(actorId, username, correlationId, occurredAt, projectId, environmentId, featureToggleId, metadata),
                Case.featureToggleDeleted(actorId, username, correlationId, occurredAt, projectId, environmentId, featureToggleId, metadata)
        );
    }

    private record Case(
            Supplier<AuditEntry> mapperCall,
            AuditType expectedAuditType,
            TargetType expectedTargetType,
            UUID expectedTargetId,
            UUID actorId,
            String username,
            UUID projectId,
            UUID environmentId,
            String correlationId,
            AuditTime time
    ) {

        static Case environmentCreated(UUID actorId, String username, String correlationId, AuditTime time,
                                       UUID projectId, UUID environmentId, Metadata metadata) {
            var event = environmentCreatedEventBuilder()
                    .projectId(projectId)
                    .environmentId(environmentId)
                    .metadata(metadata)
                    .build();

            return new Case(
                    () -> Mapper.buildAuditFor(event),
                    AuditType.ENVIRONMENT_CREATED,
                    TargetType.ENVIRONMENT,
                    environmentId,
                    actorId,
                    username,
                    projectId,
                    environmentId,
                    correlationId,
                    time
            );
        }

        static Case projectCreated(UUID actorId, String username, String correlationId, AuditTime time,
                                   UUID projectId, Metadata metadata) {
            var event = projectCreatedEventBuilder()
                    .projectId(projectId)
                    .metadata(metadata)
                    .build();

            return new Case(
                    () -> Mapper.buildAuditFor(event),
                    AuditType.PROJECT_CREATED,
                    TargetType.PROJECT,
                    projectId,
                    actorId,
                    username,
                    projectId,
                    null,
                    correlationId,
                    time
            );
        }

        static Case featureToggleCreated(UUID actorId, String username, String correlationId, AuditTime time,
                                         UUID projectId, UUID environmentId, UUID featureToggleId, Metadata metadata) {
            var event = featureToggleCreatedEventBuilder()
                    .id(featureToggleId)
                    .projectId(projectId)
                    .environmentId(environmentId)
                    .createdAt(time.occurredAt())
                    .updatedAt(time.occurredAt())
                    .type("BOOLEAN")
                    .value("true")
                    .name("TEST")
                    .description("description")
                    .metadata(metadata)
                    .build();

            return new Case(
                    () -> Mapper.buildAuditFor(event),
                    AuditType.FEATURE_TOGGLE_CREATED,
                    TargetType.FEATURE_TOGGLE,
                    featureToggleId,
                    actorId,
                    username,
                    projectId,
                    environmentId,
                    correlationId,
                    time
            );
        }

        static Case featureToggleDeleted(UUID actorId, String username, String correlationId, AuditTime time,
                                         UUID projectId, UUID environmentId, UUID featureToggleId, Metadata metadata) {
            var event = featureToggleDeletedEventBuilder()
                    .id(featureToggleId)
                    .projectId(projectId)
                    .environmentId(environmentId)
                    .metadata(metadata)
                    .build();

            return new Case(
                    () -> Mapper.buildAuditFor(event),
                    AuditType.FEATURE_TOGGLE_DELETED,
                    TargetType.FEATURE_TOGGLE,
                    featureToggleId,
                    actorId,
                    username,
                    projectId,
                    environmentId,
                    correlationId,
                    time
            );
        }
    }
}