package com.example.demo.dto;

import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    //TodoDTO에는 userId없음. userid는 애플리케이션과 데이터베이스에서 사용자를
    // 구별하기 위한 고유 식별자로 사용하기 때문에 숨길 수 있다면 숨기는 것이 보안상 알맞음.

    private String id;
    private String title;
    private boolean done;

    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title=entity.getTitle();
        this.done = entity.isDone();
    }

    public static TodoEntity toEntity(final TodoDTO dto)
    {
        return TodoEntity.builder().
                id(dto.getId()).
                title(dto.getTitle()).
                done(dto.isDone()).
                build();
        //클라이언트에게 dto로 받아서 (controller)... Entity로 변환??

    }
}
