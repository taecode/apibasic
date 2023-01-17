package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
// 스프링빈 등록
@Service
public class PostService {

    private final PostRepository postRepository;

    // 목록 조회 중간처리
    public PostListResponseDTO getList() {
        final List<PostEntity> list = postRepository.findAll();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없어용~");
        }

        // 엔터티 리스트를 DTO리스트로 변환해서 클라이언트에 응답
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(toList());

        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }

    // 개별 조회 중간처리
    public PostDetailResponseDTO getDetail(Long postNo) {

        //밑에 두 과정 한번에
        PostEntity post = postRepository
                .findById(postNo)
                .orElseThrow(()->new RuntimeException(postNo+"번 게시물이 존재하지 않음!!"))
                ;

//        PostEntity entity = post.orElseThrow(); //널이면 오류처리

//        if (post == null) throw new RuntimeException(postNo + "번 게시물이 존재하지 않음!!");

        // 엔터티를 DTO로 변환
        return new PostDetailResponseDTO(post);
    }

    // 등록 중간처리
    public PostDetailResponseDTO insert(final PostCreateDTO createDTO)
        throws RuntimeException //IllegalArgumentException, OptimisticLockingFailureException
    {
        // dto를 entity변환 작업
        final PostEntity entity = createDTO.toEntity();

        PostEntity savedPost = postRepository.save(entity); //save하다가 에러가 날 수 있다. -> 예외처리 필요
        //저장된 객체를 DTO로 변환해서 반환

        return new PostDetailResponseDTO(savedPost);
    }

    // 수정 중간 처리
    public PostDetailResponseDTO update(final Long postNo, final PostModifyDTO modifyDTO) throws RuntimeException {
        // 수정 전 데이터 조회하기
        final PostEntity entity = postRepository
                .findById(postNo)
                .orElseThrow(() -> new RuntimeException("수정 전 데이터가 존재하지 않습니다."));

        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setTitle(modTitle);
        if (modContent != null) entity.setContent(modContent);
        //       entity.setModifyDate(LocalDateTime.now());  UpdateTimestamp로인해 자동으로 들어간다

        PostEntity modifiedPost = postRepository.save(entity);
        return new PostDetailResponseDTO(modifiedPost);
    }
    // 삭제 중간처리
    public void delete(final Long postNo) throws RuntimeException {
        postRepository.deleteById(postNo);
    }
}