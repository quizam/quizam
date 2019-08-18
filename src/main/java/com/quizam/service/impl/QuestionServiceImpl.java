package com.quizam.service.impl;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import com.quizam.service.QuestionService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question findByQuestionId(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question e) {
        return questionRepository.save(e);
    }

    @Override
    public Question updateQuestion(Question e) {
        return questionRepository.save(e);
    }

    @Override
    public boolean questionExists(Question e) {
        return questionRepository.exists(Example.of(e));
    }

    @Override
    public void deleteByQuestionId(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }
}
