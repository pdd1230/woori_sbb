package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void createAnswer(Question question, String content, SiteUser siteUser) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        this.answerRepository.save(answer);
    }


    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = answerRepository.findById(id);  // class Optional --> 구글링
        if (answer.isPresent()) {    // isPresent()
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");  // throw 문법
        }
    }

    //답변내용 수정하는 메서드입니다
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        // 수정일시 저장
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

        public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
}
