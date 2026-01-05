package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
    @Id   // db table의 pk 로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //신규 record 가 insert 될때 자동 id 값을 1 씩 증가
    private Integer id;

    @Column(columnDefinition = "TEXT") // field 타입 text로 지정  예시) Mysql --> varchar
    private String content;

    private LocalDateTime createDate ;

    // 질문 답변 --> 부모 자식 --> 하나의 질문에 답변이 복수개 -> 답변은 Many --> 질문은 One
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;   // 사용자 1명이 여러개의 답변을 작성 가능함

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;      //중복 허용 하지 않음 --> 추천은 1개의 질문에 1명, 1번만 등록 가능
}
