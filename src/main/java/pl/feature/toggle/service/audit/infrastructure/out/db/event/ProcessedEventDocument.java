package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@AllArgsConstructor
@Getter
class ProcessedEventDocument {

    @Id
    private final UUID id;

}
