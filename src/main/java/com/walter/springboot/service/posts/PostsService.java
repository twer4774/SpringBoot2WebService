package com.walter.springboot.service.posts;


import com.walter.springboot.domain.posts.Posts;
import com.walter.springboot.domain.posts.PostsRepository;
import com.walter.springboot.web.dto.PostsResponseDto;
import com.walter.springboot.web.dto.PostsSaveRequestDto;
import com.walter.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
