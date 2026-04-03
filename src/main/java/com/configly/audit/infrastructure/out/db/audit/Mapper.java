package com.configly.audit.infrastructure.out.db.audit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import com.configly.audit.domain.*;

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
                entry.time().toInstant(),
                toChangeDocs(entry.changes())
        );
    }

    static AuditEntry toDomain(AuditEntryDocument document) {
        return new AuditEntry(
                AuditEntryId.of(document.id()),
                AuditType.valueOf(document.type()),
                AuditTarget.build(TargetType.valueOf(document.targetType()), document.targetId()),
                AuditContext.build(document.projectId(), document.environmentId(), document.correlationId()),
                toAuditChanges(document.changes()),
                AuditActor.build(document.actorId(), document.username()),
                AuditTime.from(document.occurredAt())
        );
    }

    private static AuditChanges toAuditChanges(List<AuditEntryDocument.ChangeDocument> changeDocs) {
        return AuditChanges.of(
                changeDocs.stream()
                        .map(c -> AuditChanges.build(c.field(), c.before(), c.after()))
                        .toList()
        );
    }

    private static List<AuditEntryDocument.ChangeDocument> toChangeDocs(AuditChanges changes) {
        return changes.changes().stream()
                .map(c -> new AuditEntryDocument.ChangeDocument(c.field(), c.before(), c.after()))
                .toList();
    }

}
