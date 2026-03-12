package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
class MongoAuditQueryRepository implements AuditQueryRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public AuditEntry findById(AuditEntryId auditEntryId) {
        return repository.findById(auditEntryId.value())
                .map(Mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Audit entry not found"));
    }

    @Override
    public List<AuditEntry> findByTargetId(UUID targetId) {
        return repository.findByTargetId(targetId)
                .stream()
                .map(Mapper::toDomain)
                .toList();
    }
}
