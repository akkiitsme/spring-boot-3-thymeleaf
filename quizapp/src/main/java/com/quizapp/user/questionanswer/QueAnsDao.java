package com.quizapp.user.questionanswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QueAnsDao extends JpaRepository<QuestionAnswer,Integer> {

    @Query(value = "SELECT COALESCE((SELECT COUNT(DISTINCT user_id) FROM trx_question_answer),0) AS user_count",nativeQuery = true)
    int getCountByUserId();
}
