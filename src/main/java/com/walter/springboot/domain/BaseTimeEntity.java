package com.walter.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 사옥할 경우 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) //Auditing기능 포함
public abstract class BaseTimeEntity {

    @CreatedDate //생성되어 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //변경할 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}
