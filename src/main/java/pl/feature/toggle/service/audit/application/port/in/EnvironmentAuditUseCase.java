package pl.feature.toggle.service.audit.application.port.in;

import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;

public interface EnvironmentAuditUseCase {

    void handle(EnvironmentCreated event);

}
