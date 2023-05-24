package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    // AWS 로드 밸런스는 기본 경로인 "/"에 http 요청을 보내서
    // 애플리케이션이 정상적으로 동작중인지 확인함
    @GetMapping("/")
    public String healthCheck(){
        return "The service is up and running...";
    }
}
