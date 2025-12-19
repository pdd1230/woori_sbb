package com.mysite.sbb.answer;

import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;  // 추가

    @PostMapping("/create/{id}")   // {id} 는 질문 Entity의 id 값
    public String createAnswer(Model model,
                               @PathVariable Integer id,
                               @RequestParam(value="content") String content) {  // textarea --> name ="content"
        Question question = this.questionService.getQuestion(id);
        // TODO: 답변을 저장한다 --> 로직 처리 순서상 해야 할일
        answerService.createAnswer(question, content); // 답볍을 저장 --> service 호출

        return String.format("redirect:/question/detail/%s", id);
    }
}

