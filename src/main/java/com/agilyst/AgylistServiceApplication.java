package com.agilyst;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgylistServiceApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(AgylistServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println("sadafasdf");
  }
}
