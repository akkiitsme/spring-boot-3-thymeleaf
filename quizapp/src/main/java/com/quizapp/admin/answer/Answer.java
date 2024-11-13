package com.quizapp.admin.answer;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "lu_answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    private int questionId;

    private String answer;

    private Integer status=1;

    private Date createdOn = new Date();

}
