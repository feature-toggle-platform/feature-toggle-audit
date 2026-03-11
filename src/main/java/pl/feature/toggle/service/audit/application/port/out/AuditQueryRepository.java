package pl.feature.toggle.service.audit.application.port.out;

import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

public interface AuditQueryRepository {

    AuditEntry findById(AuditEntryId auditEntryId);

}
