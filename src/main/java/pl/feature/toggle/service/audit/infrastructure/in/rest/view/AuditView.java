package pl.feature.toggle.service.audit.infrastructure.in.rest.view;

import pl.feature.toggle.service.audit.domain.AuditEntry;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

public record AuditView(
        String id,
        String type,
        TargetView target,
        ContextView context,
        List<ChangeView> changes,
        ActorView actor,
        LocalDateTime occurredAt
) {

    public static AuditView from(AuditEntry auditEntry) {
        return new AuditView(
                auditEntry.id().value(),
                auditEntry.type().name(),
                buildTargetView(auditEntry),
                buildContextView(auditEntry),
                buildChangesView(auditEntry),
                buildActorView(auditEntry),
                auditEntry.time().occurredAt()
        );
    }

    private static ActorView buildActorView(AuditEntry auditEntry) {
        return new ActorView(
                auditEntry.actor().actorId(),
                auditEntry.actor().username()
        );
    }

    private static List<ChangeView> buildChangesView(AuditEntry auditEntry) {
        var auditChanges = auditEntry.changes();
        if (auditChanges.hasChanges()) {
            return auditChanges.changes()
                    .stream()
                    .map(it -> new ChangeView(
                            it.field(),
                            it.before(),
                            it.after()
                    ))
                    .toList();
        }
        return emptyList();
    }

    private static ContextView buildContextView(AuditEntry auditEntry) {
        return new ContextView(
                auditEntry.context().projectId().toString(),
                auditEntry.context().environmentId().toString(),
                auditEntry.context().correlationId()
        );
    }

    private static TargetView buildTargetView(AuditEntry auditEntry) {
        return new TargetView(
                auditEntry.target().type().name(),
                auditEntry.target().targetId().toString()
        );
    }

    public record TargetView(
            String target,
            String targetId
    ) {
    }

    public record ContextView(
            String projectId,
            String environmentId,
            String correlationId
    ) {
    }

    public record ChangeView(
            String field,
            String before,
            String after
    ) {
    }

    public record ActorView(
            String actorId,
            String username
    ) {
    }


}
