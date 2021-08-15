package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	GreetingsController controller = new GreetingsController();

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller.authentication(), "This should work");
	}
}