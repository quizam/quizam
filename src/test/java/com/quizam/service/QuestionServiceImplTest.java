package com.quizam.service;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionServiceImpl questionService;

    private Question dummyQuestion;
    private List<Question> questionList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dummyQuestion = Question.builder()
                .questionId(1L)
                .question("dummy")
                .options(Arrays.asList("option1", "option2"))
                .answers(Arrays.asList("option1"))
                .build();


        questionList.add(Question.builder()
                .questionId(1L)
                .question("dummy")
                .options(Arrays.asList("option1", "option2"))
                .answers(Arrays.asList("option1"))
                .build());
        questionList.add(Question.builder()
                .questionId(2L)
                .question("dummy")
                .options(Arrays.asList("option1", "option2"))
                .answers(Arrays.asList("option1"))
                .build());
    }


    @Test
    void findByQuestionId() {

        when(questionRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(dummyQuestion));

        Question returnQuestion = questionService.findByQuestionId(anyLong());

        verify(questionRepository, atLeastOnce()).findById(anyLong());

        assertNotNull(returnQuestion);
        assertEquals(dummyQuestion, returnQuestion);

    }

    @Test
    void findAll() {

        when(questionRepository.findAll()).thenReturn(questionList);

        List<Question> returnQuestionsList = questionService.findAll();

        verify(questionRepository, atLeastOnce()).findAll();

        assertNotNull(returnQuestionsList);
        assertEquals(2, returnQuestionsList.size());
    }

    @Test
    void saveQuestion() {
        when(questionRepository.save(any(Question.class))).thenReturn(dummyQuestion);

        Question returnQuestion = questionService.saveQuestion(dummyQuestion);

        verify(questionRepository, atLeastOnce()).save(any(Question.class));

        assertNotNull(returnQuestion);
        assertEquals(dummyQuestion, returnQuestion);
    }


    @Test
    void updateQuestion() {

        when(questionRepository.save(any(Question.class))).thenReturn(dummyQuestion);

        Question returnQuestion = questionService.updateQuestion(dummyQuestion);

        verify(questionRepository, atLeastOnce()).save(any(Question.class));

        assertNotNull(returnQuestion);
        assertEquals(dummyQuestion, returnQuestion);

    }

    @Test
    void questionExists() {
        when(questionRepository.exists(any())).thenReturn(true);

        questionService.questionExists(dummyQuestion);

        verify(questionRepository, atLeastOnce()).exists(any());
    }

    @Test
    void deleteByQuestionId() {
        questionService.deleteByQuestionId(anyLong());

        verify(questionRepository, atLeastOnce()).deleteById(anyLong());

    }

    @Test
    void deleteAll() {
        questionService.deleteAll();

        verify(questionRepository, atLeastOnce()).deleteAll();

    }
}