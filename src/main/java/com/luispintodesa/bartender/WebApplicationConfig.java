package com.luispintodesa.bartender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

  // Create managed bean to allow autowiring
  @Bean
  public AuthenticationInterceptor authenticationInterceptor() {
    return new AuthenticationInterceptor();
  }

  // Register the filter with the Spring container
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(authenticationInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/**/*.css", "/**/*.jpg");
  }
}
