package pl.feature.toggle.service.audit.application.port.out;

import pl.feature.toggle.service.audit.domain.AuditEntry;

public interface AuditRepository {

    void save(AuditEntry auditEntry);

}
