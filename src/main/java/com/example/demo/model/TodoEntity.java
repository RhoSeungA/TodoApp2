package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor // 매개변수 없는 생성자 구현
@AllArgsConstructor // 모든 멤버변수를 매개변수로 받느 ㄴ생상자 구현.
@Data // 멤버변수의 Getter/Setter 구현
@Entity // 테이블 자동 생성?
@Table(name="todo")
public class TodoEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
