package com.mysite.sbb.answer;

import com.mysite.sbb.question.QuestionForm;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.question.Question;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;



    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable Integer id,
                               @Valid AnswerForm answerForm,   // 2줄 추가
                               BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        //if 문으로 bindingResult 에 eror를 체크하여 true이면 question_detail 에서 수정토록 다시 뿌려줌(에러 메세지 같이 보냄)
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.createAnswer(question, answerForm.getContent()); // content --> answerForm.getContent()로 가져옴

        return String.format("redirect:/question/detail/%s", id);
    }
}

