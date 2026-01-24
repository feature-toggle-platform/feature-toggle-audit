package pl.feature.toggle.service.audit.domain;

import java.util.Collection;
import java.util.List;

public record AuditChanges(
        List<AuditChange> changes
) {

    public static AuditChange build(String field, String before, String after) {
        return new AuditChange(field, before, after);
    }

    public static AuditChanges empty() {
        return new AuditChanges(List.of());
    }

    public static AuditChanges of(Collection<AuditChange> changes) {
        return new AuditChanges(List.copyOf(changes));
    }

    public boolean hasChanges() {
        return !changes.isEmpty();
    }

    public record AuditChange(
            String field,
            String before,
            String after
    ) {
    }
}
