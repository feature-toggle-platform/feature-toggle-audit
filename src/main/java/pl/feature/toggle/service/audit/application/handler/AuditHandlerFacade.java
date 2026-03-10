package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuditHandlerFacade {

    public static AuditUseCase createAuditUseCase(AuditRepository auditRepository) {
        return new AuditUseCaseHandler(auditRepository);
    }

}
