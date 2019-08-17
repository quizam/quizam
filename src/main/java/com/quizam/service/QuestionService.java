package com.quizam.service;

import com.quizam.domain.Question;

import java.util.List;

public interface QuestionService {
    Question saveQuestion(Question e);

    Question findByQuestionId(String questionId);

    void deleteByQuestionId(String questionId);

    void updateQuestion(Question e);

    boolean questionExists(Question e);

    List<Question> findAll();

    void deleteAll();
}
