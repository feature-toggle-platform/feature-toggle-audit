package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;

@AllArgsConstructor
class MongoAuditRepository implements AuditRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public void save(AuditEntry auditEntry) {
        var document = Mapper.toDocument(auditEntry);
        repository.save(document);
    }
}
