package com.example.demo.conf;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class ValueHolderConfiguration {
  String foo = "bar";

  @Bean
  @Primary
  public String foo() {
    return this.foo;
  }
}
