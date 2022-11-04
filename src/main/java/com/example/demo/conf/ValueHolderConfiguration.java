package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValueHolderConfiguration {
  @Value("${stringval}")
  String foo;

  @Bean
  public String foo() {
    return this.foo;
  }
}
