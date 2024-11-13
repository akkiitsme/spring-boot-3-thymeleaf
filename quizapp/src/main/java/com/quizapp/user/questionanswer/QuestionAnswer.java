package com.quizapp.user.questionanswer;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "trx_question_answer")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int queAnsId;

    private int userId;

    private int questionId;

    private int answerId;

    private Integer status=1;

    private Date createdOn = new Date();

}
