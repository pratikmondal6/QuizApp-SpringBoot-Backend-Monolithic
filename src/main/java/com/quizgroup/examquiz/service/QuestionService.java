package com.quizgroup.examquiz.service;

import com.quizgroup.examquiz.dao.QuestionDao;
import com.quizgroup.examquiz.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;


    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }

    public List<Question> getAllQuestionByCategory(String category) {

        return questionDao.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question){

        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
