package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(id);  // class Optional --> 구글링
        if (question.isPresent()) {    // isPresent()
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");  // throw 문법
        }

    }
}
