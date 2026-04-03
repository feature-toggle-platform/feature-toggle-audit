package com.configly.audit.infrastructure.out.db.event;

import lombok.AllArgsConstructor;
import com.configly.contracts.shared.EventId;
import com.configly.event.processing.api.NonTransactionalProcessedEventRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
class MongoProcessedEventRepository implements NonTransactionalProcessedEventRepository {

    private static final Duration PROCESSING_TIMEOUT = Duration.ofMinutes(5);

    private final SpringDataProcessedEventRepository repository;

    @Override
    public boolean tryStartProcessing(EventId eventId) {
        Optional<ProcessedEventDocument> existing = repository.findById(eventId.id());

        if (existing.isEmpty()) {
            repository.insert(new ProcessedEventDocument(
                    eventId.id(),
                    ProcessedEventDocument.Status.PROCESSING,
                    Instant.now()
            ));
            return true;
        }

        var doc = existing.get();
        if (doc.getStatus() == ProcessedEventDocument.Status.PROCESSED) {
            return false;
        }

        var now = Instant.now();
        if (doc.getStartedAt().plus(PROCESSING_TIMEOUT).isBefore(now)) {
            repository.save(new ProcessedEventDocument(
                    eventId.id(),
                    ProcessedEventDocument.Status.PROCESSING,
                    now
            ));
            return true;
        }

        return false;
    }

    @Override
    public void markProcessed(EventId eventId) {
        repository.save(new ProcessedEventDocument(
                eventId.id(),
                ProcessedEventDocument.Status.PROCESSED,
                Instant.now()
        ));
    }

    @Override
    public void clearProcessing(EventId eventId) {
        repository.deleteById(eventId.id());
    }
}
