package pl.feature.toggle.service.audit.domain;

import java.time.LocalDateTime;

public record AuditEntry(
        AuditEntryId id,
        AuditType type,
        AuditTarget target,
        AuditContext context,
        AuditChanges changes,
        AuditActor actor,
        LocalDateTime occurredAt
) {
}

