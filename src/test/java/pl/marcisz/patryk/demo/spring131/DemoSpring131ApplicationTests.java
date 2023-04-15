package pl.marcisz.patryk.demo.spring131;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.marcisz.patryk.demo.spring131.repository.TranslationRepository;

@SpringBootTest
class DemoSpring131ApplicationTests {

	@Autowired
	TranslationRepository translationRepository;

	@Test
	void contextLoads() {
	}

}
