package pl.feature.toggle.service.audit;

import org.springframework.boot.SpringApplication;

public class TestFeatureToggleAuditApplication {

	public static void main(String[] args) {
		SpringApplication.from(FeatureToggleAuditApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
