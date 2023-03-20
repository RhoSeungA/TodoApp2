package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//RestAPI 구현
@RestController //http관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다.
@RequestMapping("test")
public class TestController {
    @GetMapping("/testGetMapping") //이 메서드의 리소스와 http메서드를 지정한다.
    //클라이언트가 이 리소스에 대해 get메서드로 요청하면, @GetMapping에 연결된 컨트롤러가 실행.
    public String testController(){
        return "Hello World, 노승아";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! ID "+id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id)
    {
        return "Hello World, 노승아" + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO)
    {
        return "hello world! id : " +testRequestBodyDTO.getId()+" message : "+ testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list=new ArrayList<>();
        list.add("seunga");
        list.add("sangwon");
        ResponseDTO<String> response=
                ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list=new ArrayList<>();
        list.add("seunga");
        list.add("sangwon");
        ResponseDTO<String> response=
                ResponseDTO.<String>builder().data(list).build();

        //return ResponseEntity.badRequest().body(response);
        return ResponseEntity.ok().body(response);

    }


    //required = false : 매개변수 없어도 에러 안남.
    //PathVariable --> localhost:8080/test/123
    // RequestParam --> ?id=123
    // RequestBody --> 보통 반환하고자 하는 리소스가 복잡할때 사용.
}
