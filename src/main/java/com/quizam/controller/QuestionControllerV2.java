package com.quizam.controller;

import com.quizam.config.QuizamConstants;
import com.quizam.service.GraphQLService;
import graphql.ExecutionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(
        tags = "Questions for test"
)

@RestController
@RequestMapping(QuizamConstants.API_URL+QuizamConstants.APP_V2+"/questions")
public class QuestionControllerV2 {

    private GraphQLService graphQLService;

    public QuestionControllerV2(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @ApiOperation(
            value = "List of questions",
            notes = "This API fetches the Quizam questions with provided criteria."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 422, message = "Invalid input")
            }
    )
    @PostMapping
    public ResponseEntity<Object> getAllQuestions(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
