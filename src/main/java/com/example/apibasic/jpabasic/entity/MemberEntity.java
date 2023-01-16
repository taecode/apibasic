package com.example.apibasic.jpabasic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="userId")
//JPA
@Entity //JPA의 Entity객체
@Table(name = "tbl_member") //table 이름 변경
public class MemberEntity {

    @Id //기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성 전략 mysql,mariadb에서 sequence 역할 IDENTITY
    @Column(name = "user_code") //컬럼 이름 변경
    private Long userId; //회원 식별 코드 (기본키)

    @Column(nullable = false, unique = true, length = 30) //NOT NULL 제약조건, UNIQUE 제약조건(중복 방지)
    private String account; //계정명

    @Column(nullable = false)
    private String password; //패스워드

    @Column(name = "user_nick",nullable = false)
    private String nickName;//닉네임

    @Enumerated(EnumType.STRING)//ORDINAL:순번(0부터시작), STRING: 순수 문자열, ORDINAL로 하면 앞에서 부터 0번 1번 부여 (MALE=0..)
    private Gender gender; //성별

    @CreationTimestamp //INSERT 시점에 서버시간을 자동으로 입력
    private LocalDateTime joinDate; //가입일자와 시간

    @UpdateTimestamp //UPDATE 시점에 서버시간을 자동으로 입력
    private LocalDateTime modifyDate; //정보 수정 시간


}
