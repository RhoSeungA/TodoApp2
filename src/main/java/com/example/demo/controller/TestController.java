package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//RestAPI 구현
@RestController //http관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다. /// JSON을 리턴하는 웹서비스임을 명시
@RequestMapping("test") //리소스
public class TestController {
    @GetMapping("/testGetMapping") //이 메서드의 리소스와 http메서드를 지정한다. /// path설정. get메서드 사용
    //클라이언트가 이 리소스에 대해 get메서드로 요청하면, @GetMapping에 연결된 컨트롤러가 실행.
    public String testController(){
        return "Hello World, 노승아";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! ID "+id;
    }

    @GetMapping("/testRequestParam") // /testRequestParam?id=123
    public String testControllerRequestParam(@RequestParam(required = false) int id)
    {
        return "Hello World, 노승아" + id;
    }

    @GetMapping("/testRequestBody") // 반환하고자 하는 리소스가 복잡할때
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) // 이거 매개변수는 RequestBody로
    // 날아온 json을 TestRequestBodyDto로 변환해 가져오라는 뜻 --> 클라이언트는 요청 바디로 json형태으 문자열 넘겨줌.
    {
        return "hello world! id : " +testRequestBodyDTO.getId()+" message : "+ testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody") // 문자열 보다 복잡한 오브젝트 같은 거를 리턴하려면?? RestController어노테이션 안에 @responsebody 있음.
    // ReaponseBody라는 것은 이 클래스의 메서드가 리턴하는 것은 웹서비스의 ResponseBody라는 뜻. 즉
    // 메서드가 리턴할때 스프링은 리턴된 오브젝트를 json형태로 바꾸고 httpResponse에 담아 반환한다는 뜻
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
        return ResponseEntity.ok().body(response); // 헤더와 HTTP Status를 조작할 수 있음.

    }


    //required = false : 매개변수 없어도 에러 안남.
    //PathVariable --> localhost:8080/test/123
    // RequestParam --> ?id=123
    // RequestBody --> 보통 반환하고자 하는 리소스가 복잡할때 사용.
}
