package pl.feature.toggle.service.audit.infrastructure.support;

import pl.feature.toggle.service.audit.StubSupport;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

import java.util.List;
import java.util.UUID;

public class AuditRepositoryStub implements AuditQueryRepository {

    private final StubSupport<AuditEntry> findById =
            StubSupport.forMethod("findById(AuditEntryId)");

    private final StubSupport<List<AuditEntry>> findByTargetId =
            StubSupport.forMethod("findByTargetId(TargetId)");

    public void findByIdReturns(AuditEntry value) {
        findById.willReturn(value);
    }

    public void findByIdThrows(RuntimeException ex) {
        findById.willThrow(ex);
    }

    public void findByTargetIdReturns(List<AuditEntry> value) {
        findByTargetId.willReturn(value);
    }

    public void findByTargetIdThrows(RuntimeException ex) {
        findByTargetId.willThrow(ex);
    }

    public void reset() {
        findById.reset();
        findByTargetId.reset();
    }

    @Override
    public AuditEntry findById(AuditEntryId auditEntryId) {
        return findById.get();
    }

    @Override
    public List<AuditEntry> findByTargetId(UUID targetId) {
        return findByTargetId.get();
    }
}
