package com.osswa.yayaLimitusTest.DTO;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionDto {

    private String id;
    private String surveyId;
    private String questionText;
    private Integer position;

}
