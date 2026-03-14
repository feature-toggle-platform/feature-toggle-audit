package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.domain.*;
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
import pl.feature.toggle.service.contracts.shared.Changes;
import pl.feature.toggle.service.contracts.shared.Metadata;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class AuditBuilder {

    static AuditEntry buildAuditFor(EnvironmentCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(EnvironmentStatusChanged event) {
        var target = buildEnvironmentTarget(event.environmentId());
        var actor = buildActor(event.metadata());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_STATUS_CHANGED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(EnvironmentTypeChanged event) {
        var actor = buildActor(event.metadata());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var target = buildEnvironmentTarget(event.environmentId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_TYPE_CHANGED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(EnvironmentUpdated event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var changes = buildChanges(event.changes());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_UPDATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(ProjectCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildProjectTarget(event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(ProjectUpdated event) {
        var target = buildProjectTarget(event.projectId());
        var actor = buildActor(event.metadata());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_UPDATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(ProjectStatusChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildProjectTarget(event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_STATUS_CHANGED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleUpdated event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_UPDATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleStatusChanged event) {
        var target = buildFeatureToggleTarget(event.id());
        var actor = buildActor(event.metadata());
        var context = AuditContext.forFeatureToggle(event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_STATUS_CHANGED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleValueChanged event) {
        var target = buildFeatureToggleTarget(event.id());
        var actor = buildActor(event.metadata());
        var changes = buildChanges(event.changes());
        var context = AuditContext.forFeatureToggle(event.environmentId(), event.metadata().correlationId());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_VALUE_CHANGED, target, context, changes, actor, time);
    }

    private static AuditActor buildActor(Metadata metadata) {
        return AuditActor.build(metadata.actorId(), metadata.username());
    }

    private static AuditTarget buildFeatureToggleTarget(UUID featureToggleId) {
        return AuditTarget.build(TargetType.FEATURE_TOGGLE, featureToggleId);
    }

    private static AuditTarget buildEnvironmentTarget(UUID environmentId) {
        return AuditTarget.build(TargetType.ENVIRONMENT, environmentId);
    }

    private static AuditTarget buildProjectTarget(UUID projectId) {
        return AuditTarget.build(TargetType.PROJECT, projectId);
    }

    private static AuditChanges buildChanges(Changes changes) {
        return AuditChanges.of(changes.changes().stream()
                .map(it -> AuditChanges.build(it.field(), it.before(), it.after()))
                .toList());
    }
}
