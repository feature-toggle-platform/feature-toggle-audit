package com.configly.audit.application.port.out;

import com.configly.audit.domain.AuditEntry;

public interface AuditCommandRepository {

    void save(AuditEntry auditEntry);

}
