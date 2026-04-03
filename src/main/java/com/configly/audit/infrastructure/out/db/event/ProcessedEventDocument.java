package com.configly.audit.infrastructure.out.db.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document("processed_events")
@AllArgsConstructor
@Getter
class ProcessedEventDocument {

    @Id
    private final UUID id;
    private final Status status;
    private final Instant startedAt;

    enum Status {
        PROCESSING,
        PROCESSED
    }

}
