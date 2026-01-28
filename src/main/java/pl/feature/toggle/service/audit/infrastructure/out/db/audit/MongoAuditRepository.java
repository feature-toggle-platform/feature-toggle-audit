package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

@AllArgsConstructor
class MongoAuditRepository implements AuditRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public void save(AuditEntry auditEntry) {
        var document = Mapper.toDocument(auditEntry);
        repository.save(document);
    }

    @Override
    public AuditEntry findById(AuditEntryId auditEntryId) {
        return repository.findById(auditEntryId.value())
                .map(Mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Audit entry not found"));
    }
}
