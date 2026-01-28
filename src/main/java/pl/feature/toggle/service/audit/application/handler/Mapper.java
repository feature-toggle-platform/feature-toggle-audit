package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.domain.*;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;
import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;
import pl.feature.toggle.service.contracts.event.projects.ProjectCreated;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class Mapper {

    static AuditEntry buildAuditFor(EnvironmentCreated event) {
        var actor = AuditActor.build(event.metadata().actorId(), event.metadata().username());
        var target = AuditTarget.build(TargetType.ENVIRONMENT, event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntryBuilder.record(AuditType.ENVIRONMENT_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(ProjectCreated event) {
        var actor = AuditActor.build(event.metadata().actorId(), event.metadata().username());
        var target = AuditTarget.build(TargetType.PROJECT, event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntryBuilder.record(AuditType.PROJECT_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleCreated event) {
        var actor = AuditActor.build(event.metadata().actorId(), event.metadata().username());
        var target = AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntryBuilder.record(AuditType.FEATURE_TOGGLE_CREATED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleDeleted event) {
        var target = AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id());
        var actor = AuditActor.build(event.metadata().actorId(), event.metadata().username());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntryBuilder.record(AuditType.FEATURE_TOGGLE_DELETED, target, context, changes, actor, time);
    }

    static AuditEntry buildAuditFor(FeatureToggleUpdated event) {
        var actor = AuditActor.build(event.metadata().actorId(), event.metadata().username());
        var target = AuditTarget.build(TargetType.FEATURE_TOGGLE, event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event);
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntryBuilder.record(AuditType.FEATURE_TOGGLE_UPDATED, target, context, changes, actor, time);
    }

    private static AuditChanges buildChanges(FeatureToggleUpdated event) {
        return AuditChanges.of(event.changes().changes().stream()
                .map(it -> AuditChanges.build(it.field(), it.before(), it.after()))
                .toList());
    }
}
