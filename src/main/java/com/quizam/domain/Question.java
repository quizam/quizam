package com.quizam.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Question extends ResourceSupport {
    @Id
    private String questionId;
    private String question;
    private List<String> options;
    private List<String> answers;
    private String author;
    private String subject;
    private String topic;
}
