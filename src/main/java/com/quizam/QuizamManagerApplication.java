package com.quizam;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import com.quizam.service.common.SequenceGeneratorService;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class QuizamManagerApplication {
    private static final Logger LOGGER = Logger.getLogger(QuizamManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QuizamManagerApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(QuestionRepository questionRepository, SequenceGeneratorService sequenceGeneratorService) {
        return (args) -> {
            questionRepository.deleteAll();
            Question questionOne = questionRepository.save(new Question(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME),"Which service is used to save objects in AWS?", Arrays.asList("EBS", "EFS", "S3", "EKS"), Arrays.asList("S3"), "Jeff Barr", "AWS", "storage"));
            Question questionTwo = questionRepository.save(new Question(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME),"You have five CloudFormation templates; each template is for a different application architecture. This architecture varies between your blog apps and your gaming apps. What determines the cost of using the CloudFormation templates?", Arrays.asList("The time it takes to build the architecture with Cloud Formation.", "Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds.", "0.10$ per template per month", "0.1$ per template per month"),
                    Arrays.asList("Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds."), "Jeff Barr", "AWS", "Cloud Formation"));
        };
    }
}
