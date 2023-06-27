package com.dim.configuracion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class SeguridadConfig {


    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().disable()
                .csrf().disable()
                // TODO: Actvar en producción
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeHttpRequests(autorizacion -> autorizacion
                        .requestMatchers(("/swagger-ui/**")).permitAll()
                        .requestMatchers(("/v3/api-docs/**")).permitAll()
                        .requestMatchers(("/auth/**")).permitAll()
                        .anyRequest().permitAll()) // TODO Eliminar en producción
                .build();
    }


     */
}



