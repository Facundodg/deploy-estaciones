package com.dim.configuracion;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    FilterRegistrationBean<?> corsFilterRegistrationBean() {
        final UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        final FilterRegistrationBean<?> filterRegistrationBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> a773bbbfe2dd277c0b81e920e2a9c2f7c34f8c11
