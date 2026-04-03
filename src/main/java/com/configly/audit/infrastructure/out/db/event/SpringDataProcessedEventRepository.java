package com.configly.audit.infrastructure.out.db.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringDataProcessedEventRepository extends MongoRepository<ProcessedEventDocument, UUID> {
}
