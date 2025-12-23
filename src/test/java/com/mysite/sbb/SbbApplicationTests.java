package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SbbApplicationTests {

    // DI(의존성 주입) --> questionRepository 라는 객체를 스프링 만들어서 제공해줌
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

//    @Test  //중복 생성 막기 위해 주석 처리
    void testJpa() {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now()); // static 메서드 호출 --> class명.메서드명
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now()); // static 메서드 호출 --> class명.메서드명
        this.questionRepository.save(q2);

    }

    @Test
    void makeTestData()  {
        for (int i = 1; i <= 300; i++) {
            // %03d --> d 숫자(디지털) --> 빈자리를 0 --> 전체자릿수 3
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }
    }

}
