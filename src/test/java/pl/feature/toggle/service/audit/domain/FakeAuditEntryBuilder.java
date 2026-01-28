package pl.feature.toggle.service.audit.domain;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.UUID.randomUUID;

public class FakeAuditEntryBuilder {
    private AuditEntryId id;
    private AuditType type;
    private AuditTarget target;
    private AuditContext context;
    private AuditChanges changes;
    private AuditActor actor;
    private AuditTime time;

    private FakeAuditEntryBuilder() {
        this.id = AuditEntryId.create();
        this.type = AuditType.FEATURE_TOGGLE_CREATED;
        this.target = AuditTarget.build(TargetType.FEATURE_TOGGLE, randomUUID());
        this.context = AuditContext.build(randomUUID(), randomUUID(), randomUUID().toString());
        this.changes = AuditChanges.of(List.of(AuditChanges.build("key", "oldValue", "newValue")));
        this.actor = AuditActor.build(randomUUID().toString(), randomUUID().toString());
        this.time = AuditTime.from(LocalDateTime.now());
    }

    public static FakeAuditEntryBuilder fakeAuditEntryBuilder() {
        return new FakeAuditEntryBuilder();
    }

    public FakeAuditEntryBuilder withId(AuditEntryId id) {
        this.id = id;
        return this;
    }

    public FakeAuditEntryBuilder withType(AuditType type) {
        this.type = type;
        return this;
    }

    public FakeAuditEntryBuilder withTarget(AuditTarget target) {
        this.target = target;
        return this;
    }

    public FakeAuditEntryBuilder withContext(AuditContext context) {
        this.context = context;
        return this;
    }

    public FakeAuditEntryBuilder withChanges(AuditChanges changes) {
        this.changes = changes;
        return this;
    }

    public FakeAuditEntryBuilder withActor(AuditActor actor) {
        this.actor = actor;
        return this;
    }

    public FakeAuditEntryBuilder withOccurredAt(LocalDateTime occurredAt) {
        this.time = AuditTime.from(occurredAt);
        return this;
    }

    public AuditEntry build() {
        return new AuditEntry(id, type, target, context, changes, actor, time);
    }
}
