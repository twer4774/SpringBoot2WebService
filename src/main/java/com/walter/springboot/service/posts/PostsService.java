package com.walter.springboot.service.posts;


import com.walter.springboot.domain.posts.Posts;
import com.walter.springboot.domain.posts.PostsRepository;
import com.walter.springboot.web.dto.PostsListResponseDto;
import com.walter.springboot.web.dto.PostsResponseDto;
import com.walter.springboot.web.dto.PostsSaveRequestDto;
import com.walter.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 쿼리를 날리는 부분이 없음 - JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
     * Entity객체의 값만 변경하면 별도의 Update 쿼리를 날릴 필요없음 - 더티체킹(dirty checking)
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    //전체 조회
    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도가 개선됨 - 등록, 수정, 삭제 기능이 없을 때 사용하면 좋음
    public List<PostsListResponseDto> findAllDesc(){
        //람다식. postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 변환하는 메소드
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
