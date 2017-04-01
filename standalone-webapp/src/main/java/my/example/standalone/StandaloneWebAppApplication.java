package my.example.standalone;

import my.example.standalone.conversion.SourceToTargetConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class StandaloneWebAppApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SourceToTargetConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(StandaloneWebAppApplication.class, args);
    }
}
