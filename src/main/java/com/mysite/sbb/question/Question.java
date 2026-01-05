package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id   // db table의 pk 로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //신규 record 가 insert 될때 자동 id 값을 1 씩 증가
    private Integer id ;

    @Column(length = 200) // 컬럼의 길이를 200으로 지정
    private String subject;

    @Column(columnDefinition = "TEXT") // field 타입 text로 지정  예시) Mysql --> varchar
    private String content;

    private LocalDateTime createDate ;

    // 질문 / 답변 --> 부모 / 자식 , 질문 삭제되면 답변들도 삭제 되어야 한다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;  // 복수개의 답변을 저장하려면 --> List 구조 --> 제너릭 --> Answer 타입 지정

    @ManyToOne
    private SiteUser author;   // 사용자 1명이 질문을 여러개 작성 가능함

    private LocalDateTime modifyDate ;

    @ManyToMany
    Set<SiteUser> voter;      //중복 허용 하지 않음 --> 추천은 1개의 질문에 1명, 1번만 등록 가능

}
