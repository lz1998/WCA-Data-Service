package xin.lz1998.wcads.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xin.lz1998.wcads.domain.converter.EventConverter;
import xin.lz1998.wcads.domain.converter.GenderConverter;
import xin.lz1998.wcads.domain.converter.ResultTypeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter());
        registry.addConverter(new ResultTypeConverter());
        registry.addConverter(new GenderConverter());
    }
}
