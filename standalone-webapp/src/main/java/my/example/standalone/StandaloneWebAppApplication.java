package my.example.standalone;

import my.example.standalone.conversion.SourceEntityToAgeEntityConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableCaching
public class StandaloneWebAppApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SourceEntityToAgeEntityConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(StandaloneWebAppApplication.class, args);
    }
}
