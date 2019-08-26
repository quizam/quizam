package com.quizam.service.datafetcher;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllQuestionsDataFetcher implements DataFetcher<List<Question>>{

    private QuestionRepository questionRepository;

    public AllQuestionsDataFetcher(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return questionRepository.findAll();
    }
}
