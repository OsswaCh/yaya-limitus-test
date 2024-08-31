package com.osswa.yayaLimitusTest.Model;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Setter
@Getter
//todo: check if setter qnd getter on the outside define the functions on the inside
@Data
@Entity
@Table (name = "Answers")
public class Answer {

    //getter and getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name = "survey_Response_Id")
    private UUID surveyResponseId;

    @Column (name = "question_Id")
    private UUID questionId;

    @Column (name = "Answer")
    private String answer;

    @Column (name = "created_at")
    private LocalDateTime createdAt;


    //Relations
    @ManyToOne (cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private SurveyResponse surveyResponse;


    //constructor
    public Answer (){
        this.surveyResponseId = null;
        this.questionId = null;
        this.answer = "Answer DNE";
        this.createdAt = null;

    }

    public Answer (UUID surveyResponseId ,UUID questionId, String answer ){

        this.surveyResponseId = surveyResponseId;
        this.questionId = questionId;
        this.answer = answer;
        this.createdAt = LocalDateTime.now();

    }

}
