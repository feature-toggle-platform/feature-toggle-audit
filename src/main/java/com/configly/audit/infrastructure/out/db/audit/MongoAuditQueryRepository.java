package com.configly.audit.infrastructure.out.db.audit;

import lombok.AllArgsConstructor;
import com.configly.audit.application.port.out.AuditQueryRepository;
import com.configly.audit.domain.AuditEntry;
import com.configly.audit.domain.AuditEntryId;
import com.configly.audit.domain.exception.AuditEntryNotFoundException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
class MongoAuditQueryRepository implements AuditQueryRepository {

    private final SpringDataAuditEntryMongoRepository repository;

    @Override
    public AuditEntry findById(AuditEntryId auditEntryId) {
        return repository.findById(auditEntryId.value())
                .map(Mapper::toDomain)
                .orElseThrow(() -> new AuditEntryNotFoundException(auditEntryId));
    }

    @Override
    public List<AuditEntry> findByTargetId(UUID targetId) {
        return repository.findByTargetId(targetId)
                .stream()
                .map(Mapper::toDomain)
                .toList();
    }
}
