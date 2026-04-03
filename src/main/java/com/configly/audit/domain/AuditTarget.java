package com.configly.audit.domain;

import java.util.UUID;

public record AuditTarget(
        TargetType type,
        UUID targetId
) {

    public static AuditTarget build(TargetType type, UUID targetId) {
        return new AuditTarget(type, targetId);
    }

}
