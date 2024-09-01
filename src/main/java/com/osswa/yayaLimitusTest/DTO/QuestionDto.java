package com.osswa.yayaLimitusTest.DTO;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class QuestionDto {

    private UUID id;
    private UUID surveyId;
    private String questionText;
    private Integer position;

}
