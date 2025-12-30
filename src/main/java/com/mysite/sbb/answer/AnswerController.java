package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable Integer id,
                               @Valid AnswerForm answerForm,   // 2줄 추가
                               BindingResult bindingResult ,
                               Principal principal) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        //if 문으로 bindingResult 에 error를 체크하여 true이면 question_detail 에서 수정토록 다시 뿌려줌(에러 메세지 같이 보냄)
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.createAnswer(question, answerForm.getContent(), siteUser); // content --> answerForm.getContent()로 가져옴

        return String.format("redirect:/question/detail/%s", id);
    }
}

