package com.osswa.yayaLimitusTest.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SurveyDto {
    private UUID id;
    private String title;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private List<QuestionDto> questions;
}
