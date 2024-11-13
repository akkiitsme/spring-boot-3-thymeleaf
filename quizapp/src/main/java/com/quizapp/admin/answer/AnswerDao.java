package com.quizapp.admin.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDao extends JpaRepository<Answer,Integer> {
    List<Answer> findByQuestionIdAndStatus(int questionId,int status);
}
