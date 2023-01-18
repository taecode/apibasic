package com.example.apibasic.post.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 게시물의 데이터 자바빈즈
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="postNo")
@Builder
@Entity
@Table(name="tbl_post")
public class PostEntity {

 //   public static long sequence = 1L; // 연속된 일련번호

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no") //기본키는 notnull, unique 자동으로 붙는다
    private Long postNo; // 게시물 식별번호

    @Column(nullable = false)
    private String writer; // 작성자

    @Column(nullable = false)
    private String title; // 제목

    private String content; // 내용

    //양방향 매핑시
    @OneToMany(mappedBy = "post")
   private List<HashTagEntity> hashTags=new ArrayList<>(); // 해시태그 목록

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") ->DTO로
    @CreationTimestamp
    private LocalDateTime createDate; // 작성 시간

    @UpdateTimestamp //처음 insert에 들어가고 update되면 수정된다
    private LocalDateTime modifyDate; // 수정 시간




}
