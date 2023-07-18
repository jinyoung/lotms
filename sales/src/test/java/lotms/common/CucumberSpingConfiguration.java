package lotms.common;

import io.cucumber.spring.CucumberContextConfiguration;
import lotms.SalesApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { SalesApplication.class })
public class CucumberSpingConfiguration {}
