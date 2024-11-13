package com.quizapp.admin.subject;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "lu_subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    private String subjectName;

    private int subjectStatus;

    private int status = 1;

    private Date createdOn = new Date();
}
