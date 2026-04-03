package com.configly.audit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.configly.audit.infrastructure.support.AuditRepositorySpy;
import com.configly.audit.infrastructure.support.AuditRepositoryStub;

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
