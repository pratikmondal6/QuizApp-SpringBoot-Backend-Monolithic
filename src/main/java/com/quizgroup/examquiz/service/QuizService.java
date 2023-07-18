package com.quizgroup.examquiz.service;

import com.quizgroup.examquiz.dao.QuestionDao;
import com.quizgroup.examquiz.dao.QuizDao;
import com.quizgroup.examquiz.entity.Question;
import com.quizgroup.examquiz.entity.QuestionWrapper;
import com.quizgroup.examquiz.entity.Quiz;
import com.quizgroup.examquiz.entity.UserResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String titleQ) {

        List<Question> questionList =
                questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(titleQ);
        quiz.setQuestions(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q: questionFromDb){
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionForUser.add(questionWrapper);



        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);




    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> userResponseList) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questionList = quiz.getQuestions();

        int right= 0;
        int i= 0;

        for(UserResponse userResponse: userResponseList){

            if(userResponse.getResponse().equals(questionList.get(i).getRightAnswer()));
            right++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
