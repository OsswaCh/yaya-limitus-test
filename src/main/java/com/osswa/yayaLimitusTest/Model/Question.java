package com.osswa.yayaLimitusTest.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "questions")

@Setter
@Getter
public class Question {

  //getters ans setters

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column (name = "survey_id")
    private UUID surveyId;

  @Column(name = " question_text")
    private String questionText;

  @Column (name = "position")
    private Integer position;

  //Relations

    //todo: recheck the mappedBy issue here (probably it is not needed)
    @ManyToOne (cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Survey> surveys;

    @ManyToMany (mappedBy = "questions", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyResponse>  surveyResponses ;
    //constructor

    public Question(){

        //todo: check if this needs to be made into an error instead
        this.surveyId = null;
        this.questionText = "question does not exist";
        this.position = null;

    }

    //todo check if position needs to be entered or calculated here
    public Question(UUID surveyId, String questionText, Integer position){

        this.surveyId = surveyId;
        this.questionText = questionText;
        this.position = position;

    }
}
