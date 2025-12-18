package com.example.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ShopApplication {

	@Value("${spring.profiles.active:default}")
    private String activeProfile;

    @PostConstruct
    public void log() {
        System.out.println("Active Spring profile: " + activeProfile);
    }

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
