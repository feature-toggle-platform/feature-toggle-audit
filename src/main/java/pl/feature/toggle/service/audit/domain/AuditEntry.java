package pl.feature.toggle.service.audit.domain;

public record AuditEntry(
        AuditEntryId id,
        AuditType type,
        AuditTarget target,
        AuditContext context,
        AuditChanges changes,
        AuditActor actor,
        AuditTime time
) {
}

