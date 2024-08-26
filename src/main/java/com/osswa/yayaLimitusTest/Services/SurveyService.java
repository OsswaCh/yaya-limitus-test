package com.osswa.yayaLimitusTest.Services;


import com.osswa.yayaLimitusTest.DTO.QuestionDto;
import com.osswa.yayaLimitusTest.DTO.SurveyCreateRequest;
import com.osswa.yayaLimitusTest.DTO.SurveyDto;
import com.osswa.yayaLimitusTest.Model.Survey;
import com.osswa.yayaLimitusTest.repository.SurveyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j

// receives data from a web request (SurveyWebController) and then assigns the value to a new Survey entity.
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionService questionService;

    public UUID create(SurveyCreateRequest request){
        Survey survey = new Survey(request.getTitle(), request.getDescription(), request.isActive());
        log.info("Creating survey: {}", survey);
        Survey savedSurvey = surveyRepository.save(survey);
        return savedSurvey.getId();
    }

    public SurveyDto getSurvey (UUID surveyId){
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Survey not found") );
        return convertToDto(survey, true);
    }

    //fetching all surveys with pagination
    public List<SurveyDto> getSurveys(){
        Page<Survey> surveys = surveyRepository.findAll(PageRequest.of(0, 50));
        List<Survey> surveyList = surveys.getContent();

        return surveys.stream()
                .map(survey -> convertToDto(survey, false))
                .collect(Collectors.toList());
    }

    private SurveyDto convertToDto(Survey survey, boolean includeQuestions){
        List<QuestionDto> questions = includeQuestions?
                questionService.getQuestions(survey.getId()) : null;

        return SurveyDto.builder()
                .id(survey.getId())
                .createdAt(survey.getCreatedAt())
                .description(survey.getDescription())
                .title(survey.getTitle())
                .isActive(survey.is_active())
                .questions(questions)
                .build();
    }

}
