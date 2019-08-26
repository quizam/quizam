package com.quizam.service.datafetcher;

import com.quizam.domain.Question;
import com.quizam.exceptions.QuestionNotAvailableException;
import com.quizam.repository.QuestionRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionDataFetcher implements DataFetcher<Question>{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long questionId = dataFetchingEnvironment.getArgument("id");
        Optional<Question> optUser =  questionRepository.findById(questionId);
        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            return null;
        }
    }
}
