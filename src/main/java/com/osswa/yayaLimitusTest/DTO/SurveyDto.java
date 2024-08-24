package com.osswa.yayaLimitusTest.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class SurveyDto {
    private String id;
    private String title;
    private String description;
    private boolean isActive;
    private Date createdAt;
    private List<QuestionDto> questions;
}
