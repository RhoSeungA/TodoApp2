package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 내부에 @Component 어노테이션을 포함하고 있으며
// 비즈니스 로직을 수행하는 서비스 레이어임을 알려주는 역할을 하는 어노테이션
public class TodoService {
//    public String testService(){
//        return "test Service";
//    }

    @Autowired
    private TodoRepository repository;

    public String testService(){
        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("my first todo item").build();

        //TodoEntity 저장
        repository.save(entity);

        //TodoEntity 검색
        TodoEntity savedEntity=repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
}
