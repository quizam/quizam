package com.quizam.controller;

import com.quizam.domain.Question;
import com.quizam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public HttpEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            questions.forEach(e -> e.add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withRel("questions")));
            questions.forEach(e -> e.add(linkTo(methodOn(QuestionController.class).getQuestionById(e.getQuestionId())).withSelfRel()));
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public HttpEntity<Question> getQuestionById(@PathVariable("id") String questionId) {
        Question byQuestionId = questionService.findByQuestionId(questionId);
        if (byQuestionId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            byQuestionId.add(linkTo(methodOn(QuestionController.class).getQuestionById(byQuestionId.getQuestionId())).withSelfRel());
            return new ResponseEntity<>(byQuestionId, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/question/", method = RequestMethod.POST)
    public HttpEntity<?> saveQuestion(@RequestBody Question e) {
        if (questionService.questionExists(e)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Question question = questionService.saveQuestion(e);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/questions/question/{id}")
                    .buildAndExpand(question.getQuestionId()).toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(location);
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public HttpEntity<?> updateQuestion(@PathVariable("id") String id, @RequestBody Question e) {
        Question byQuestionId = questionService.findByQuestionId(id);
        if(byQuestionId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            byQuestionId.setQuestion(e.getQuestion());
            byQuestionId.setOptions(e.getOptions());
            byQuestionId.setAnswers(e.getAnswers());
            byQuestionId.setAuthor(e.getAuthor());
            byQuestionId.setSubject(e.getSubject());
            byQuestionId.setTopic(e.getTopic());
            questionService.updateQuestion(byQuestionId);
            byQuestionId.add(linkTo(methodOn(QuestionController.class).getQuestionById(byQuestionId.getQuestionId())).withSelfRel());
            return new ResponseEntity<>(byQuestionId, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String questionId) {
        questionService.deleteByQuestionId(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/question/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll() {
        questionService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
