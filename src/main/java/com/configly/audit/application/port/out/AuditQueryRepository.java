package com.configly.audit.application.port.out;

import com.configly.audit.domain.AuditEntry;
import com.configly.audit.domain.AuditEntryId;

import java.util.List;
import java.util.UUID;

public interface AuditQueryRepository {

    AuditEntry findById(AuditEntryId auditEntryId);

    List<AuditEntry> findByTargetId(UUID targetId);

}
