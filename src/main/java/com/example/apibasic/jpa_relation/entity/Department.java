package com.example.apibasic.jpa_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter @ToString(exclude = "employees")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "deptId")
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId; //부서번호
    private String deptName; //부서명

    //양방향매핑에서는 상대방 엔터티의 정보를 수정할 수는 없고
    //단순 읽기 기능(조회)만 지원한다.
    //mappedBy에는 상대방 엔터티의 조인되는 필드명을 씀
    //OneToMany는 필요하면 사용, ManyToOne은 관계있을 경우 무조건 사용
    @OneToMany(mappedBy = "department") //Department = One
    private List<Employee> employees=new ArrayList<>(); //객체지향에서는 양방향 매핑 가능


}
