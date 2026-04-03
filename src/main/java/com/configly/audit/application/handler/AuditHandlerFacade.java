package com.configly.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import com.configly.audit.application.port.in.AuditUseCase;
import com.configly.audit.application.port.out.AuditCommandRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuditHandlerFacade {

    public static AuditUseCase createAuditUseCase(AuditCommandRepository auditCommandRepository) {
        return new AuditUseCaseHandler(auditCommandRepository);
    }

}
