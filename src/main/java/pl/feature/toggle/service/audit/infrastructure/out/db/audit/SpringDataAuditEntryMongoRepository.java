package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringDataAuditEntryMongoRepository extends MongoRepository<AuditEntryDocument, String> {
}
