package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="email")}) // ddl생성시 유니크 제약 조건 만듬.
public class UserEntity { // 사용자 정보를 데이터베이스에 저장하기 위한 엔티티 클래스.
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id; //사용자에게 고유하게 부여되는 id

    @Column(nullable=false)
    private String username; // 사용자 이름

    @Column(nullable=false)
    private String email; // 아이디와 같은 기능

    @Column(nullable=false)
    private String password; //이메일과 패스워드로 로그인 한다구 생각하면됌..


}
