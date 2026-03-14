package pl.feature.toggle.service.audit.infrastructure.in.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;
import pl.feature.toggle.service.audit.infrastructure.in.rest.view.AuditView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/api/audit")
@AllArgsConstructor
class AuditController {

    private final AuditQueryRepository auditQueryRepository;

    @GetMapping("/projects/{projectId}")
    List<AuditView> getAuditForProject(@PathVariable String projectId) {
        var auditEntries = auditQueryRepository.findByTargetId(UUID.fromString(projectId));
        return auditEntries.stream()
                .map(AuditView::from)
                .toList();
    }

    @GetMapping("/environments/{environmentId}")
    List<AuditView> getAuditForEnvironment(@PathVariable String environmentId) {
        var auditEntries = auditQueryRepository.findByTargetId(UUID.fromString(environmentId));
        return auditEntries.stream()
                .map(AuditView::from)
                .toList();
    }

    @GetMapping("/feature-toggles/{featureToggleId}")
    List<AuditView> getAuditForFeatureToggle(@PathVariable String featureToggleId) {
        var auditEntries = auditQueryRepository.findByTargetId(UUID.fromString(featureToggleId));
        return auditEntries.stream()
                .map(AuditView::from)
                .toList();
    }

}
