package com.configly.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import com.configly.audit.application.port.out.AuditCommandRepository;
import com.configly.audit.domain.AuditEntry;

@AllArgsConstructor
class MongoAuditCommandRepository implements AuditCommandRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public void save(AuditEntry auditEntry) {
        var document = Mapper.toDocument(auditEntry);
        repository.save(document);
    }
}
