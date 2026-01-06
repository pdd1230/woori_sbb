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

    public void vote(Answer answer , SiteUser siteUser ) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }

    // 답변 컨트롤러에서 답변이 등록된 위치로 이동하기 위해서 --> 반드시 Answer 객체가 필요함 --> 서버가 답변 id를 알아함
    // 기존에는 Answer 객체를 반환을 안함 --> 답변 id 몰라요 --> 앵거링이 불가능
    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

}
