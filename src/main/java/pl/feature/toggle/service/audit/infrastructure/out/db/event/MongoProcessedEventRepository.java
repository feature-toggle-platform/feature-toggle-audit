package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import com.mongodb.DuplicateKeyException;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.contracts.shared.EventId;
import pl.feature.toggle.service.event.processing.api.ProcessedEventRepository;

@AllArgsConstructor
class MongoProcessedEventRepository implements ProcessedEventRepository {

    private final SpringDataProcessedEventRepository repository;

    @Override
    public boolean tryMarkProcessed(EventId eventId) {
        try {
            repository.insert(new ProcessedEventDocument(eventId.id()));
            return true;
        } catch (DuplicateKeyException exception) {
            return false;
        }
    }
}
