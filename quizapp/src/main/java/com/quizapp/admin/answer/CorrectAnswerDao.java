package com.quizapp.admin.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerDao extends JpaRepository<CorrectAnswer,Integer> {

    CorrectAnswer findByQuestionId(int questionId);
}
