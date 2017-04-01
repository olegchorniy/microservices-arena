package my.example.cassandra.spring.config;

import my.example.cassandra.spring.entity.UserAddressViewModelToUserConverter;
import my.example.cassandra.spring.entity.UserToExtendedUserAddressViewModelConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConverterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserAddressViewModelToUserConverter());
        registry.addConverter(new UserToExtendedUserAddressViewModelConverter());
    }
}
