package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;

@AllArgsConstructor
class MongoAuditCommandRepository implements AuditCommandRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public void save(AuditEntry auditEntry) {
        var document = Mapper.toDocument(auditEntry);
        repository.save(document);
    }
}
