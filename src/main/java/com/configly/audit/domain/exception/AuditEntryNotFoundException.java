package com.configly.audit.domain.exception;

import com.configly.audit.domain.AuditEntryId;

public class AuditEntryNotFoundException extends RuntimeException {
    public AuditEntryNotFoundException(AuditEntryId auditEntryId) {
        super("Audit entry with id " + auditEntryId + " not found");
    }
}
