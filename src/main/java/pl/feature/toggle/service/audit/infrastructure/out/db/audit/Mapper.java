package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.domain.AuditChanges;
import pl.feature.toggle.service.audit.domain.AuditEntry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
final class Mapper {

    static AuditEntryDocument toDocument(AuditEntry entry) {
        return new AuditEntryDocument(
                entry.id().value(),
                entry.type().name(),
                entry.context().projectId(),
                entry.context().environmentId(),
                entry.context().correlationId(),
                entry.target().type().name(),
                entry.target().targetId(),
                entry.actor().actorId(),
                entry.actor().username(),
                toInstantUtc(entry.occurredAt()),
                toChangeDocs(entry.changes())
        );
    }

    private static Instant toInstantUtc(LocalDateTime ldt) {
        return ldt.toInstant(ZoneOffset.UTC);
    }

    private static List<AuditEntryDocument.ChangeDocument> toChangeDocs(AuditChanges changes) {
        return changes.changes().stream()
                .map(c -> new AuditEntryDocument.ChangeDocument(c.field(), c.before(), c.after()))
                .toList();
    }

}
