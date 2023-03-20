package com.example.demo.dto;

import lombok.Data;

@Data // getter setter 추가
public class TestRequestBodyDTO { //요청시 전달된 값을 담는데 사용할 객체
    private int id;
    private String message;
}
