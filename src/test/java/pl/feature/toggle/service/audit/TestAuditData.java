package pl.feature.toggle.service.audit;

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

import java.time.Instant;
import java.util.UUID;

import static pl.feature.toggle.service.contracts.fake.event.FakeEnvironmentCreatedBuilder.fakeEnvironmentCreatedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeEnvironmentStatusChangedBuilder.fakeEnvironmentStatusChangedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeEnvironmentTypeChangedBuilder.fakeEnvironmentTypeChangedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeEnvironmentUpdatedBuilder.fakeEnvironmentUpdatedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeFeatureToggleCreatedBuilder.fakeFeatureToggleCreatedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeFeatureToggleStatusChangedBuilder.fakeFeatureToggleStatusChangedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeFeatureToggleUpdatedBuilder.fakeFeatureToggleUpdatedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeFeatureToggleValueChangedBuilder.fakeFeatureToggleValueChangedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeProjectCreatedBuilder.fakeProjectCreatedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeProjectStatusChangedBuilder.fakeProjectStatusChangedBuilder;
import static pl.feature.toggle.service.contracts.fake.event.FakeProjectUpdatedBuilder.fakeProjectUpdatedBuilder;

public class TestAuditData {

    public static final ProjectCreated PROJECT_CREATED_EVENT =
            fakeProjectCreatedBuilder().build();

    public static final ProjectUpdated PROJECT_UPDATED_EVENT =
            fakeProjectUpdatedBuilder().build();

    public static final ProjectStatusChanged PROJECT_STATUS_CHANGED_EVENT =
            fakeProjectStatusChangedBuilder().build();

    public static final EnvironmentCreated ENVIRONMENT_CREATED_EVENT =
            fakeEnvironmentCreatedBuilder().build();

    public static final EnvironmentUpdated ENVIRONMENT_UPDATED_EVENT =
            fakeEnvironmentUpdatedBuilder().build();

    public static final EnvironmentStatusChanged ENVIRONMENT_STATUS_CHANGED_EVENT =
            fakeEnvironmentStatusChangedBuilder().build();

    public static final EnvironmentTypeChanged ENVIRONMENT_TYPE_CHANGED_EVENT =
            fakeEnvironmentTypeChangedBuilder().build();

    public static final FeatureToggleCreated FEATURE_TOGGLE_CREATED_EVENT =
            fakeFeatureToggleCreatedBuilder().build();

    public static final FeatureToggleUpdated FEATURE_TOGGLE_UPDATED_EVENT =
            fakeFeatureToggleUpdatedBuilder().build();

    public static final FeatureToggleStatusChanged FEATURE_TOGGLE_STATUS_CHANGED_EVENT =
            fakeFeatureToggleStatusChangedBuilder().build();

    public static final FeatureToggleValueChanged FEATURE_TOGGLE_VALUE_CHANGED_EVENT =
            fakeFeatureToggleValueChangedBuilder().build();

    public static AuditEntry buildExpectedAuditEntry(ProjectCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildProjectTarget(event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_CREATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(ProjectUpdated event) {
        var actor = buildActor(event.metadata());
        var target = buildProjectTarget(event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_UPDATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(ProjectStatusChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildProjectTarget(event.projectId());
        var context = AuditContext.forProject(event.projectId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.PROJECT_STATUS_CHANGED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(EnvironmentCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_CREATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(EnvironmentUpdated event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_UPDATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(EnvironmentStatusChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_STATUS_CHANGED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(EnvironmentTypeChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildEnvironmentTarget(event.environmentId());
        var context = AuditContext.forEnvironment(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.ENVIRONMENT_TYPE_CHANGED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(FeatureToggleCreated event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = AuditChanges.empty();
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_CREATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(FeatureToggleUpdated event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_UPDATED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(FeatureToggleStatusChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
        var time = AuditTime.from(event.metadata().occurredAt());
        return AuditEntry.build(AuditType.FEATURE_TOGGLE_STATUS_CHANGED, target, context, changes, actor, time);
    }

    public static AuditEntry buildExpectedAuditEntry(FeatureToggleValueChanged event) {
        var actor = buildActor(event.metadata());
        var target = buildFeatureToggleTarget(event.id());
        var context = AuditContext.forFeatureToggle(event.projectId(), event.environmentId(), event.metadata().correlationId());
        var changes = buildChanges(event.changes());
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
        return AuditChanges.of(
                changes.changes().stream()
                        .map(it -> AuditChanges.build(it.field(), it.before(), it.after()))
                        .toList()
        );
    }

    public static AuditEntry randomAuditEntry() {
        return AuditEntry.build(
                AuditType.PROJECT_CREATED,
                AuditTarget.build(TargetType.PROJECT, UUID.randomUUID()),
                AuditContext.forProject(UUID.randomUUID(), UUID.randomUUID().toString()),
                AuditChanges.empty(),
                AuditActor.build(UUID.randomUUID().toString(), "admin"),
                AuditTime.from(Instant.now())
        );
    }

    public static AuditEntry randomAuditEntry(UUID targetId) {
        return AuditEntry.build(
                AuditType.PROJECT_CREATED,
                AuditTarget.build(TargetType.PROJECT, targetId),
                AuditContext.forProject(UUID.randomUUID(), UUID.randomUUID().toString()),
                AuditChanges.empty(),
                AuditActor.build(UUID.randomUUID().toString(), "admin"),
                AuditTime.from(Instant.now())
        );
    }

}
