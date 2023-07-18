package com.quizgroup.examquiz.controller;


import com.quizgroup.examquiz.entity.QuestionWrapper;
import com.quizgroup.examquiz.entity.UserResponse;
import com.quizgroup.examquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam  int numQ,
                                             @RequestParam String titleQ){

        return quizService.createQuiz(category,numQ,titleQ);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){

        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitAnswer(@PathVariable Integer id,
                                                @RequestBody List<UserResponse> userResponseList){


        return quizService.calculateResult(id,userResponseList);

    }



}
