package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        //findAll() : 해당 엔티티 테이블에 있는 모든 데이터를 조회
        //save() : 대상 엔티티를 DB에 저장
        //saveAll() : Iterable 가능한 객체를 저장
        //delete() : 데이터베이스에서 대상 엔티티를 삭제


        //TodoEntity 검색
        TodoEntity savedEntity=repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }


    public List<TodoEntity> create(final TodoEntity entity)
    {
        validate(entity);

        repository.save(entity);
        log.info("entity id : {} is saved. ",entity.getId());
        return  repository.findByUserId(entity.getUserId()); // 이사람이 만든 모든 todolist반환
    }


    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    private static void validate(TodoEntity entity) {
        if(entity ==null){log.warn("entity cannot be null"); throw new RuntimeException("entity cannot be null");}
        if(entity.getUserId()==null){log.warn("unknown user"); throw new RuntimeException("Unknown user");}
    }



}
