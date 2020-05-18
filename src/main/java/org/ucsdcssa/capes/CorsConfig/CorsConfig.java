package org.ucsdcssa.capes.CorsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/namelesscape/**")
                        .allowedOrigins("http://ucsdcssa.com/namelesscape:8081","http://ucsdcssa.com:5500",
                                "http://104.236.138.205/namelesscape:8081","http://104.236.138.205:5500",
                                "http://70.95.171.168/namelesscape:8081","http://70.95.171.168:5500");//允许域名访问，如果*，代表所有域名
            }
        };
    }
}