package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//HTTP 응답으로 사용할 DTO필요.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
//TodoDTO뿐만 아니라 이후의 다른 모델의 DTO도 ResponseDTO를 이용해 리턴 할수 있도록 제네릭 사용.

public class ResponseDTO<T> { // http 응답으로 사용할 dto가 필요해서 만들었음?.. 책에..
    private String error;
    private List<T> data;
}

//new ResponseDTO<String> <TodoDTO>

