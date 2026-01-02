package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import pl.feature.toggle.service.contracts.shared.EventId;

final class Mapper {

    static ProcessedEventDocument create(EventId eventId) {
        return new ProcessedEventDocument(eventId.id());
    }

}
