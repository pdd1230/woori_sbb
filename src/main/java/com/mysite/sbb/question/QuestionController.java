package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

//    private final QuestionRepository questionRepository;  //주석 처리
      private final QuestionService questionService;  // questionService 객체로 수정

    @GetMapping("/question/list")
    public String list(Model model) {
//        List<Question> questionList = QuestionRepository.getList();  // 주석 처리
        List<Question> questionList = this.questionService.getList();  // this.questionService 가 대신 역할을 하도록 수정
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

}
