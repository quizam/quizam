package com.quizam.controller;

import com.quizam.config.QuizamConstants;
import com.quizam.domain.Question;
import com.quizam.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


@Api(
        tags = "Questions for test"
)

@RestController
@RequestMapping(QuizamConstants.API_URL+QuizamConstants.APP_V1+"/questions")
public class QuestionControllerV1 {


    private QuestionService questionService;

    public QuestionControllerV1(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation(
            value = "List of questions",
            notes = "This API fetches the Quizam questions with provided criteria."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @GetMapping("/list/")
    public HttpEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            questions.forEach(e -> e.add(linkTo(methodOn(QuestionControllerV1.class).getAllQuestions()).withRel("questions")));
            questions.forEach(e -> e.add(linkTo(methodOn(QuestionControllerV1.class).getQuestionById(e.getQuestionId())).withSelfRel()));
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Question by questionId",
            notes = "This API fetches the Quizam question by provided question Id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @GetMapping("/question/{id}")
    public HttpEntity<Question> getQuestionById(@PathVariable("id") Long questionId) {
        Question byQuestionId = questionService.findByQuestionId(questionId);
        if (byQuestionId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            byQuestionId.add(linkTo(methodOn(QuestionControllerV1.class).getQuestionById(byQuestionId.getQuestionId())).withSelfRel());
            byQuestionId.add(linkTo(methodOn(QuestionControllerV1.class).getAnswersByQuestionId(byQuestionId.getQuestionId())).withRel("answers"));
            return new ResponseEntity<>(byQuestionId, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Question by questionId",
            notes = "This API fetches the Quizam question's answers by provided question id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @GetMapping("/answers/{id}")
    public HttpEntity<List<String>> getAnswersByQuestionId(@PathVariable("id") Long questionId) {
        Question byQuestionId = questionService.findByQuestionId(questionId);
        if (byQuestionId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            byQuestionId.add(linkTo(methodOn(QuestionControllerV1.class).getAnswersByQuestionId(byQuestionId.getQuestionId())).withSelfRel());
            return new ResponseEntity<>(byQuestionId.getAnswers(), HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Add question to the question set",
            notes = "This API provides facility to add a new question to Quizam question set."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @PostMapping("/question/")
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

    @ApiOperation(
            value = "Modify question in the question set",
            notes = "This API provides facility to modify the existing question of Quizam question set."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @PutMapping("/question/{id}")
    public HttpEntity<?> updateQuestion(@PathVariable("id") Long id, @RequestBody Question e) {
        Question byQuestionId = questionService.findByQuestionId(id);
        if (byQuestionId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            byQuestionId.setQuestion(e.getQuestion());
            byQuestionId.setOptions(e.getOptions());
            byQuestionId.setAnswers(e.getAnswers());
            byQuestionId.setAuthor(e.getAuthor());
            byQuestionId.setSubject(e.getSubject());
            byQuestionId.setTopic(e.getTopic());
            byQuestionId = questionService.updateQuestion(byQuestionId);
            byQuestionId.add(linkTo(methodOn(QuestionControllerV1.class).getQuestionById(byQuestionId.getQuestionId())).withSelfRel());
            return new ResponseEntity<>(byQuestionId, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Remove question by id",
            notes = "This API provides facility to remove the existing question from Quizam question set."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @DeleteMapping("/question/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long questionId) {
        questionService.deleteByQuestionId(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Remove all questions",
            notes = "This API provides facility to remove all the existing question from Quizam question set."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input"),
            }
    )
    @DeleteMapping("/all/questions/")
    public ResponseEntity<?> deleteAll() {
        questionService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
