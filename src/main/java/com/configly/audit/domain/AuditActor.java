package com.configly.audit.domain;

public record AuditActor(
        String actorId,
        String username
) {

    public static AuditActor build(String actorId, String username) {
        return new AuditActor(actorId, username);
    }

}
