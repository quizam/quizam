package com.quizam;

import com.quizam.domain.Question;
import com.quizam.repository.QuestionRepository;
import com.quizam.service.common.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class QuizamManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizamManagerApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(QuestionRepository questionRepository, SequenceGeneratorService sequenceGeneratorService) {
        log.info("initianting test data");
        return (args) -> {
            questionRepository.deleteAll();
            questionRepository.save(
                    Question.builder()
                            .questionId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME))
                            .question("Which service is used to save objects in AWS?")
                            .options(Arrays.asList("EBS", "EFS", "S3", "EKS"))
                            .answers(Arrays.asList("S3"))
                            .author("Jeff Barr")
                            .subject("AWS")
                            .topic("Cloud Formation")
                            .build()
            );
            questionRepository.save(
                    Question.builder()
                            .questionId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME))
                            .question("You have five CloudFormation templates; each template is for a different " +
                                    "application architecture. This architecture varies between your blog apps and " +
                                    "your gaming apps. What determines the cost of using the CloudFormation templates?")
                            .options(Arrays.asList("The time it takes to build the architecture with Cloud Formation.",
                                    "Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds.",
                                    "0.10$ per template per month",
                                    "0.1$ per template per month"))
                            .answers(Arrays.asList("Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds."))
                            .author("Jeff Barr")
                            .subject("AWS")
                            .topic("storage")
                            .build()
            );
        };
    }
}
