package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {


  //과제
//    @GetMapping("/testTodo")
//    public ResponseEntity<TodoDTO> testTodo(){
//        TodoDTO response =
//                TodoDTO.builder().title("노승아").id("123").done(true).build();
//        return ResponseEntity.ok().body(response);
//    }
//
    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str=service.testService();
        List<String> list= new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response =
                ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto)
    {
        try{
            String temporaryUserId = "temporary-user";
            TodoEntity entity=TodoDTO.toEntity(dto);

            entity.setId(null);
            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = service.create(entity); // 서비스 레이어에 요청..?

            List<TodoDTO> dtos= entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList()
    {
        try{
            String temporaryUserId = "temporary-user";

            List<TodoEntity> entities = service.retrieve(temporaryUserId);

            List<TodoDTO> dtos= entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto)
    {
        String temporaryUserId = "temporary-user";
        TodoEntity entity = TodoDTO.toEntity(dto);

        entity.setUserId(temporaryUserId);

        List<TodoEntity> entities = service.update(entity);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto)
    {
        try{
            String temporaryUserId= "temporary-user";
            TodoEntity entity= TodoDTO.toEntity(dto);
            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities= service.delete(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}
