package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    public String list(Model model) {      // 인터페이스 Modek 사용
        List<Question> questionList = questionRepository.findAll();   // 메서드 findAll() --> 모든 record read 
        model.addAttribute("questionList", questionList); // 객체 model에 List<Question>  questionList 전달
        return "question_list";
    }

}
