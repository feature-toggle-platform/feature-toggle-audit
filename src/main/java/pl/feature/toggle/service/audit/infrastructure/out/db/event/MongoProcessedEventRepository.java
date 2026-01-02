package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.out.ProcessedEventRepository;
import pl.feature.toggle.service.contracts.shared.EventId;

@AllArgsConstructor
class MongoProcessedEventRepository implements ProcessedEventRepository {

    private final SpringDataProcessedEventRepository repository;

    @Override
    public boolean alreadyProcessed(EventId eventId) {
        return repository.existsById(eventId.id());
    }

    @Override
    public void markProcessed(EventId eventId) {
        repository.insert(Mapper.create(eventId));
    }
}
