package com.quizam.service;

import com.quizam.domain.Question;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question e);

    Question findByQuestionId(Long questionId);

    void deleteByQuestionId(Long questionId);

    Question updateQuestion(Question e);

    boolean questionExists(Question e);

    List<Question> findAll();

    void deleteAll();
}
