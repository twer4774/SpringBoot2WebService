package com.walter.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity //JPA의 어노테이션. 테이블과 연결될 클래스임을 나타냄. 카멜케이스네이밍으로 스네이크케이스 테이블연결(SalesManager.java -> sales_manager table)
public class Posts {

    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성 규칙. auto_increment부여
    private Long Id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타나며 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용(VARCHAR(255)가 기본인데 500으로 늘림)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
