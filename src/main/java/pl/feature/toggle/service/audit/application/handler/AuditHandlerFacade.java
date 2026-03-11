package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuditHandlerFacade {

    public static AuditUseCase createAuditUseCase(AuditCommandRepository auditCommandRepository) {
        return new AuditUseCaseHandler(auditCommandRepository);
    }

}
