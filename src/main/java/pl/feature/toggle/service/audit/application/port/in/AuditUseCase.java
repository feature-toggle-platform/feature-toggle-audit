package pl.feature.toggle.service.audit.application.port.in;

import pl.feature.toggle.service.contracts.event.environment.EnvironmentCreated;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentStatusChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentTypeChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleStatusChanged;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleValueChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectCreated;
import pl.feature.toggle.service.contracts.event.project.ProjectStatusChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectUpdated;

public interface AuditUseCase {

    void handle(FeatureToggleCreated event);

    void handle(FeatureToggleUpdated event);

    void handle(FeatureToggleStatusChanged event);

    void handle(FeatureToggleValueChanged event);

    void handle(ProjectCreated event);

    void handle(ProjectStatusChanged event);

    void handle(ProjectUpdated event);

    void handle(EnvironmentCreated event);

    void handle(EnvironmentUpdated event);

    void handle(EnvironmentStatusChanged event);

    void handle(EnvironmentTypeChanged event);


}
