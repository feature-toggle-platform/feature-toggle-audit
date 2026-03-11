package pl.feature.toggle.service.audit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pl.feature.toggle.service.audit.infrastructure.support.AuditRepositorySpy;
import pl.feature.toggle.service.audit.infrastructure.support.AuditRepositoryStub;
import pl.feature.toggle.service.contracts.shared.Metadata;

import java.util.UUID;

public abstract class AbstractUnitTest {

    protected AuditRepositoryStub auditRepositoryStub;
    protected AuditRepositorySpy auditRepositorySpy;




    @BeforeEach
    void setUp() {
        this.auditRepositorySpy = new AuditRepositorySpy();
        this.auditRepositoryStub = new AuditRepositoryStub();
    }

    @AfterEach
    void tearDown() {
        auditRepositorySpy.reset();
        auditRepositoryStub.reset();
    }

}
