package com.quizam.service.impl;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import com.quizam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question saveQuestion(Question e) {
        return questionRepository.save(e);
    }

    @Override
    public Question findByQuestionId(String questionId) {
        return questionRepository.findOne(questionId);
    }

    @Override
    public void deleteByQuestionId(String questionId) {
        questionRepository.delete(questionId);
    }

    @Override
    public void updateQuestion(Question e) {
        questionRepository.save(e);
    }

    @Override
    public boolean questionExists(Question e) {
        return questionRepository.exists(Example.of(e));
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }
}
