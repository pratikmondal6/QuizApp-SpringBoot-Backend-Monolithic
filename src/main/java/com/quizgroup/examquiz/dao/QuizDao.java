package com.quizgroup.examquiz.dao;

import com.quizgroup.examquiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
