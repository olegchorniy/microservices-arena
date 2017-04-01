package my.example.cassandra.nativeapi.config;

import my.example.cassandra.nativeapi.entity.UserAddressViewModelToUserConverter;
import my.example.cassandra.nativeapi.entity.UserToExtendedUserAddressViewModelConverter;
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
