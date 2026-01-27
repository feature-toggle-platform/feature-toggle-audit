package pl.feature.toggle.service.audit.infrastructure;

import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditTarget;

import java.util.HashMap;
import java.util.Map;

public class FakeAuditRepository implements AuditRepository {

    private final Map<AuditTarget, AuditEntry> auditEntries = new HashMap<>();

    @Override
    public void save(AuditEntry auditEntry) {
        auditEntries.put(auditEntry.target(), auditEntry);
    }

    public AuditEntry getByAuditTarget(AuditTarget target) {
        return auditEntries.get(target);
    }
}
