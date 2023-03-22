package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String> {
    // <TodoEntity,String>  앞에거는
    // 테이블에 매핑될 엔티티 클래스 // 뒤에거는 엔티티의 기본 키의 타입.


    //@Query("select * from Todo t where t.userId = ?1") //수동 쿼리 주기
    List<TodoEntity> findByUserId(String userId);

}
