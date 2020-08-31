package com.walter.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<Domain, id타입>을 이용하면 CRUD를 작성할 필요가 없다
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //전체조회
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
