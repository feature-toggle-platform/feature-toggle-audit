package com.configly.audit.infrastructure.out.db.audit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "audit_entries")
record AuditEntryDocument(
        @Id
        String id,
        @Indexed
        String type,
        @Indexed
        UUID projectId,
        @Indexed
        UUID environmentId,
        @Indexed
        String correlationId,
        @Indexed
        String targetType,
        @Indexed
        UUID targetId,
        @Indexed
        String actorId,
        String username,
        @Indexed
        Instant occurredAt,
        List<ChangeDocument> changes
) {

    record ChangeDocument(
            String field,
            String before,
            String after
    ) {

    }
}
