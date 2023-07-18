package com.quizgroup.examquiz.controller;

import com.quizgroup.examquiz.entity.Question;
import com.quizgroup.examquiz.service.QuestionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        try {
            return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){

        questionService.addQuestion(question);
        return "success";
    }

}

