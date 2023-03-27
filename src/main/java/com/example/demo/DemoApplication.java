package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 해당 클래스가 스프링 부트를 설정하는 클래스임을 의미 / 이 클래스가 있는 패키지를 베이스 패키지로 간주.
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
