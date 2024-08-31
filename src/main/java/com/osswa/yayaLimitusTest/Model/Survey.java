package com.osswa.yayaLimitusTest.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "surveys")
@Data
public class Survey {

    //getters and setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @Column
    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @Setter
    @Getter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany (mappedBy = "survey", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToOne (mappedBy = "survey", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyResponse> surveyResponses;

    //constructor
    public Survey(){
        this.title="Untitled survey";
        this.description = "No description provided";
        this.isActive = false;
        this.createdAt = LocalDateTime.now();
    }

    public Survey(String title, String description, boolean isActive){
        this.title=title;
        this.description=description;
        this.isActive= isActive;
        this.createdAt = LocalDateTime.now();
    }
    

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean is_active() {
        return isActive;
    }

    public void set_active(boolean active) {
        isActive=true;
    }
    
    

    /*
    String id;
    String title;
    String description;
    boolean is_active;
    Date createdAt;*/
}
