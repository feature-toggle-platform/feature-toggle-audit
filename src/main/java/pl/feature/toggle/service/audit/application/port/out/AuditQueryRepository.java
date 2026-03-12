package pl.feature.toggle.service.audit.application.port.out;

import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

import java.util.List;
import java.util.UUID;

public interface AuditQueryRepository {

    AuditEntry findById(AuditEntryId auditEntryId);

    List<AuditEntry> findByTargetId(UUID targetId);

}
