package com.osswa.yayaLimitusTest.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data

@Table(name = "respondents")
@Entity
public class Respondent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    // Add respondents names
    @Column(name = "first_name", nullable = false)
    private String email;

    //relations
    //todo: check if this is one to one or one to many
    @OneToMany(mappedBy = "respondents", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private SurveyResponse surveyResponse;

    //constructor
    public Respondent(){
    }
    // Parameterized constructor
    public Respondent(String email) {
        this.email = email;
    }

}
