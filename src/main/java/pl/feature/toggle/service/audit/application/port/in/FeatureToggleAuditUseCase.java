package pl.feature.toggle.service.audit.application.port.in;

import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;

public interface FeatureToggleAuditUseCase {

    void handle(FeatureToggleCreated event);

    void handle(FeatureToggleUpdated event);

    void handle(FeatureToggleDeleted event);


}
