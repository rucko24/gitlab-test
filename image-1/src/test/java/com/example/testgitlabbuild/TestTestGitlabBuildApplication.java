package com.example.testgitlabbuild;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
@Disabled
public class TestTestGitlabBuildApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestGitlabBuildApplication::main).with(TestTestGitlabBuildApplication.class).run(args);
	}

}
