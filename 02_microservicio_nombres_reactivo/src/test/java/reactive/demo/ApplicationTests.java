package reactive.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactive.demo.controller.NamesController;
import reactor.test.StepVerifier;

@SpringBootTest
class ApplicationTests {

	@Autowired
	NamesController namesController;

	@Test
	void contextLoads() {
		StepVerifier.create(namesController.getNames())
				.expectNext("John")
				.expectNext("Jane","Doe")
				.expectNextCount(1)
				.verifyComplete();
	}

}
