package com.quizam.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Question extends ResourceSupport {

    @Transient
    public static final String SEQUENCE_NAME = "question_sequence";

    @Id
    private Long questionId;
    @NonNull
    private String question;
    @NonNull
    private List<String> options;
    @NonNull
    private List<String> answers;
    private String author;
    private String subject;
    private String topic;

    public Question(Long questionId, @NonNull String question, @NonNull List<String> options, @NonNull List<String> answers, String author, String subject, String topic) {
        this.questionId = questionId;
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.author = author;
        this.subject = subject;
        this.topic = topic;
    }
}
