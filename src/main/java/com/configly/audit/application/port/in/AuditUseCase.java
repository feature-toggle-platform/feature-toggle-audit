package com.configly.audit.application.port.in;

import com.configly.contracts.event.environment.EnvironmentCreated;
import com.configly.contracts.event.environment.EnvironmentStatusChanged;
import com.configly.contracts.event.environment.EnvironmentTypeChanged;
import com.configly.contracts.event.environment.EnvironmentUpdated;
import com.configly.contracts.event.featuretoggle.FeatureToggleCreated;
import com.configly.contracts.event.featuretoggle.FeatureToggleStatusChanged;
import com.configly.contracts.event.featuretoggle.FeatureToggleUpdated;
import com.configly.contracts.event.featuretoggle.FeatureToggleValueChanged;
import com.configly.contracts.event.project.ProjectCreated;
import com.configly.contracts.event.project.ProjectStatusChanged;
import com.configly.contracts.event.project.ProjectUpdated;

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
