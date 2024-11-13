package com.quizapp.admin.question;

import com.quizapp.admin.answer.Answer;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "lu_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private int subjectId;

    private String question;

    private Integer queStatus;

    private Integer status = 1;

    private Date createdOn = new Date();

    @Transient
    private String subjectName;

    @Transient
    private List<Answer> ansList = new ArrayList<>();

    @Transient
    private int correctAnswerId;

    @Transient
    private String correctAnswer;
}
