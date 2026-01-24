package pl.feature.toggle.service.audit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuditEntryBuilder {

    public static AuditEntry record(
            AuditType type,
            AuditTarget target,
            AuditContext context,
            AuditChanges changes,
            AuditActor actor,
            LocalDateTime occurredAt
    ) {
        return new AuditEntry(AuditEntryId.create(), type, target, context, changes, actor, occurredAt);
    }

}
