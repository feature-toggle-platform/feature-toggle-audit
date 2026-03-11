package pl.feature.toggle.service.audit.infrastructure.support;

import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;

import java.util.ArrayList;
import java.util.List;

public class AuditRepositorySpy implements AuditCommandRepository {

    private final List<AuditEntry> saved = new ArrayList<>();

    private boolean failOnAnyCall;

    public void expectNoCalls() {
        failOnAnyCall = true;
    }

    public void reset() {
        saved.clear();
        failOnAnyCall = false;
    }

    @Override
    public void save(AuditEntry auditEntry) {
        if (failOnAnyCall) {
            throw new AssertionError("save should not be called");
        }

        saved.add(auditEntry);
    }

    public AuditEntry lastSaved() {
        return saved.isEmpty() ? null : saved.getLast();
    }

    public List<AuditEntry> allSaved() {
        return saved;
    }
}
