package com.daniel.delivery.servicetest;

import com.daniel.delivery.PostgresqlTestContainer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = BaseIT.Initializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public  class BaseIT {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void before() {
		jdbcTemplate.execute("TRUNCATE TABLE person RESTART IDENTITY CASCADE;");
	}

	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
			PostgresqlTestContainer.applyForLiquibase(applicationContext, "public");
		}

	}
}