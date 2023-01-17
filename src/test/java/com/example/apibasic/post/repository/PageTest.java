package com.example.apibasic.post.repository;

import com.example.apibasic.post.dto.PageRequestDTO;
import com.example.apibasic.post.dto.PageResponseDTO;
import com.example.apibasic.post.entity.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class PageTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void bulkInsert() {
        for (int i = 0; i <=433; i++) {
            PostEntity post = PostEntity.builder()
                    .title("안녕~" + i)
                    .writer("김말종" + i)
                    .content("아무말~~~~~" + i)
                    .build();
            postRepository.save(post);
        }


    }

    @Test
    void pagingTest() {

        //클라이언트가 전달한 페이지 정보
        PageRequestDTO dto = PageRequestDTO.builder()
                .page(4)
                .sizePerPage(10)
                .build();


        //페이지 정보 생성
        PageRequest pageInfo = PageRequest.of(
                dto.getPage() - 1,
                dto.getSizePerPage(),
                Sort.Direction.DESC, // 내림차 정렬
                "createDate" //정렬 기준 필드, 최신 글은 작성일자가 가장 늦다.


        );

        Page<PostEntity> postEntities = postRepository.findAll(pageInfo);

        //실제 조회된 데이터 
        List<PostEntity> contents = postEntities.getContent();


        int totalPages = postEntities.getTotalPages();
        long totalElements = postEntities.getTotalElements();
        // boolean prev = postEntities.getPageable().hasPrevious();


        System.out.println("contents = " + contents);
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        //System.out.println("prev = " + prev);

        System.out.println("contents.size() = " + contents.size());
        contents.forEach(System.out::println);
    }

    @Test
    @DisplayName("제목에 3이 포함된 결과를 검색 후 1페이지 정보를 조회해야 한다.")
    void pageTest2() {
        //given
        String title = "3";
        PageRequest pageRequest = PageRequest.of(
                3,
                10,
                Sort.Direction.DESC,
                "createDate");

        Slice<PostEntity> postEntityPage
                = postRepository.findByTitleContaining(title, pageRequest);

        List<PostEntity> contents = postEntityPage.getContent();

        boolean next = postEntityPage.hasNext();
        boolean prev = postEntityPage.hasPrevious();
        System.out.println("next = " + next);
        System.out.println("prev = " + prev);

        contents.forEach(System.out::println);
        
        //페이지 정보
        PageResponseDTO<PostEntity> dto 
                = new PageResponseDTO<PostEntity>((Page<PostEntity>)postEntityPage);

        System.out.println("dto = " + dto);
    }
}//class
