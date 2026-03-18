package pl.feature.toggle.service.audit.domain.exception;

import pl.feature.toggle.service.audit.domain.AuditEntryId;

public class AuditEntryNotFoundException extends RuntimeException {
    public AuditEntryNotFoundException(AuditEntryId auditEntryId) {
        super("Audit entry with id " + auditEntryId + " not found");
    }
}
