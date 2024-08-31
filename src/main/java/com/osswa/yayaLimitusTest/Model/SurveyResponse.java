package com.osswa.yayaLimitusTest.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "surveyResponses")
@Data

@Getter
@Setter
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name = "respondent_Id")
    private UUID respondentId;

    @Column(name = "survey_Id")
    private UUID surveyId;

    @Column (name = "started_at")
    private LocalDateTime startedAt;


    @Column (name = "completed_at")
    private LocalDateTime completedAt;


    //relations
    @OneToOne (mappedBy = "surveyResponses", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Survey survey;

    @OneToMany



    /*@RowKey
    String id;
    String respondentId;
    String surveyId;
    Date startedAT;
    Date completedAt;
*/
}
