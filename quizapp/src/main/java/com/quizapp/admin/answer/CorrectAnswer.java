package com.quizapp.admin.answer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "lu_correct_answer")
public class CorrectAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int correctAnswerId;

    private int answerId;

    private int questionId;

    private Integer status=1;

    private Date createdOn = new Date();
}
