package com.example.apibasic.post.repository;

import com.example.apibasic.post.entity.PostEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {


    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시물 정보를 데이터베이스에 저장해야 한다.")
    @Transactional
    @Rollback
    void saveTest(){
        PostEntity savePost=PostEntity.builder()
                .writer("홍길동")
                .title("호롤롤로")
                .content("하이~")
                .hashTags()
                .build();

        postRepository.save(savePost);

        Optional<PostEntity> foundPost = postRepository.findById(1L);

        PostEntity post=foundPost.get();
        assertNotNull(post);
    }


    @Test
    @DisplayName("목록 조회")
    @Transactional
    @Rollback
    void findAllTest(){

        List<PostEntity>postEntityList=postRepository.findAll();

        assertEquals(3,postEntityList.size());

    }

    @Test
    @DisplayName("개별 조회")
    @Transactional
    @Rollback
    void detailTest(){

        List<PostEntity>postEntityList=postRepository.findById()
    }

    @Test
    void abc() {

        PostRepository repository = new PostRepository();
        repository.findAll();
    }

}