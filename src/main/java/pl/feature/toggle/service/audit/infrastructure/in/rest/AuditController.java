package pl.feature.toggle.service.audit.infrastructure.in.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.audit.domain.AuditEntryId;
import pl.feature.toggle.service.audit.infrastructure.in.rest.view.AuditView;

@RestController
@RequestMapping("/rest/api/audit")
@AllArgsConstructor
class AuditController {

    private final AuditRepository auditRepository;

    @GetMapping("/projects/{projectId}")
    AuditView getAuditForProject(@PathVariable String projectId) {
        var auditEntry = auditRepository.findById(AuditEntryId.of(projectId));
        return AuditView.from(auditEntry);
    }

    @GetMapping("/environments/{environmentId}")
    AuditView getAuditForEnvironment(@PathVariable String environmentId) {
        var auditEntry = auditRepository.findById(AuditEntryId.of(environmentId));
        return AuditView.from(auditEntry);
    }

    @GetMapping("/feature-toggles/{featureToggleId}")
    AuditView getAuditForFeatureToggle(@PathVariable String featureToggleId) {
        var auditEntry = auditRepository.findById(AuditEntryId.of(featureToggleId));
        return AuditView.from(auditEntry);
    }

}
