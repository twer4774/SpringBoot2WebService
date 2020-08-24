package com.walter.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Domain, id타입>을 이용하면 CRUD를 작성할 필요가 없다
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
