package pl.feature.toggle.service.audit.infrastructure.support;

import pl.feature.toggle.service.audit.StubSupport;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

public class AuditRepositoryStub implements AuditQueryRepository {

    private final StubSupport<AuditEntry> findById =
            StubSupport.forMethod("findById(AuditEntryId)");

    public void findByIdReturns(AuditEntry value) {
        findById.willReturn(value);
    }

    public void findByIdThrows(RuntimeException ex) {
        findById.willThrow(ex);
    }

    public void reset() {
        findById.reset();
    }

    @Override
    public AuditEntry findById(AuditEntryId auditEntryId) {
        return findById.get();
    }
}
