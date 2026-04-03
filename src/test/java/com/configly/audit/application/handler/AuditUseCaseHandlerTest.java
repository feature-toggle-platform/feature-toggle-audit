package com.configly.audit.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.configly.audit.AbstractUnitTest;
import com.configly.audit.application.port.in.AuditUseCase;
import com.configly.audit.domain.AuditEntry;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static com.configly.audit.TestAuditData.*;

class AuditUseCaseHandlerTest extends AbstractUnitTest {

    private AuditUseCase sut;

    @BeforeEach
    public void setup() {
        sut = AuditHandlerFacade.createAuditUseCase(auditRepositorySpy);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("shouldSaveAuditCases")
    void should_save_audit_entry_for_supported_events(
            String testName,
            AuditInvocation invocation,
            AuditEntry expected
    ) {
        // given && when
        invocation.invoke(sut);

        // then
        var saved = auditRepositorySpy.lastSaved();

        assertThat(saved).isNotNull();
        assertThat(saved).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }

    private static Stream<Arguments> shouldSaveAuditCases() {
        return Stream.of(
                Arguments.of(
                        "[0] -> ProjectCreated",
                        invoke(useCase -> useCase.handle(PROJECT_CREATED_EVENT)),
                        buildExpectedAuditEntry(PROJECT_CREATED_EVENT)
                ),
                Arguments.of(
                        "[1] -> ProjectUpdated",
                        invoke(useCase -> useCase.handle(PROJECT_UPDATED_EVENT)),
                        buildExpectedAuditEntry(PROJECT_UPDATED_EVENT)
                ),
                Arguments.of(
                        "[2] -> ProjectStatusChanged",
                        invoke(useCase -> useCase.handle(PROJECT_STATUS_CHANGED_EVENT)),
                        buildExpectedAuditEntry(PROJECT_STATUS_CHANGED_EVENT)
                ),
                Arguments.of(
                        "[3] -> EnvironmentCreated",
                        invoke(useCase -> useCase.handle(ENVIRONMENT_CREATED_EVENT)),
                        buildExpectedAuditEntry(ENVIRONMENT_CREATED_EVENT)
                ),
                Arguments.of(
                        "[4] -> EnvironmentUpdated",
                        invoke(useCase -> useCase.handle(ENVIRONMENT_UPDATED_EVENT)),
                        buildExpectedAuditEntry(ENVIRONMENT_UPDATED_EVENT)
                ),
                Arguments.of(
                        "[5] -> EnvironmentStatusChanged",
                        invoke(useCase -> useCase.handle(ENVIRONMENT_STATUS_CHANGED_EVENT)),
                        buildExpectedAuditEntry(ENVIRONMENT_STATUS_CHANGED_EVENT)
                ),
                Arguments.of(
                        "[6] -> EnvironmentTypeChanged",
                        invoke(useCase -> useCase.handle(ENVIRONMENT_TYPE_CHANGED_EVENT)),
                        buildExpectedAuditEntry(ENVIRONMENT_TYPE_CHANGED_EVENT)
                ),
                Arguments.of(
                        "[7] -> FeatureToggleCreated",
                        invoke(useCase -> useCase.handle(FEATURE_TOGGLE_CREATED_EVENT)),
                        buildExpectedAuditEntry(FEATURE_TOGGLE_CREATED_EVENT)
                ),
                Arguments.of(
                        "[8] -> FeatureToggleUpdated",
                        invoke(useCase -> useCase.handle(FEATURE_TOGGLE_UPDATED_EVENT)),
                        buildExpectedAuditEntry(FEATURE_TOGGLE_UPDATED_EVENT)
                ),
                Arguments.of(
                        "[9] -> FeatureToggleStatusChanged",
                        invoke(useCase -> useCase.handle(FEATURE_TOGGLE_STATUS_CHANGED_EVENT)),
                        buildExpectedAuditEntry(FEATURE_TOGGLE_STATUS_CHANGED_EVENT)
                ),
                Arguments.of(
                        "[10] -> FeatureToggleValueChanged",
                        invoke(useCase -> useCase.handle(FEATURE_TOGGLE_VALUE_CHANGED_EVENT)),
                        buildExpectedAuditEntry(FEATURE_TOGGLE_VALUE_CHANGED_EVENT)
                )
        );
    }

    private static AuditInvocation invoke(AuditInvocation invocation) {
        return invocation;
    }

    @FunctionalInterface
    interface AuditInvocation {
        void invoke(AuditUseCase useCase);
    }
}