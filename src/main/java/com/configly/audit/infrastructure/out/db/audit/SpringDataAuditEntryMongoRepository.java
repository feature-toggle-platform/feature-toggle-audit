package com.configly.audit.infrastructure.out.db.audit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface SpringDataAuditEntryMongoRepository extends MongoRepository<AuditEntryDocument, String> {

    List<AuditEntryDocument> findByTargetId(UUID targetId);

}
