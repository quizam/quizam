package com.quizam.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Objects;

@Document
public class Question extends ResourceSupport{
    @Id
    private String questionId;
    private String question;
    private List<String> options;
    private List<String> answers;
    private String author;
    private String subject;
    private String topic;


    public Question(String questionId, String question, List<String> options, List<String> answers, String author, String subject, String topic) {
        this.questionId = questionId;
        this.question = question;
        this.options = options;
        this.answers = answers;
        this.author = author;
        this.subject = subject;
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        if (!super.equals(o)) return false;
        Question question1 = (Question) o;
        return getQuestionId().equals(question1.getQuestionId()) &&
                getQuestion().equals(question1.getQuestion()) &&
                getOptions().equals(question1.getOptions()) &&
                getAnswers().equals(question1.getAnswers()) &&
                getAuthor().equals(question1.getAuthor()) &&
                getSubject().equals(question1.getSubject()) &&
                getTopic().equals(question1.getTopic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuestionId(), getQuestion(), getOptions(), getAnswers(), getAuthor(), getSubject(), getTopic());
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", answers=" + answers +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
