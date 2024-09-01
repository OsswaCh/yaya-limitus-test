package com.osswa.yayaLimitusTest.Services;


import com.osswa.yayaLimitusTest.DTO.QuestionDto;
import com.osswa.yayaLimitusTest.Model.Question;
import com.osswa.yayaLimitusTest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    //logging
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    //fetching all questions for a certain survey using the id
    public List<QuestionDto> getQuestions (UUID surveyId){
        try{
        List<Question> questions = questionRepository.findBySurveyId(surveyId);
        return questions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    //Mapping a question entity to a question DTO
    private QuestionDto mapToDto (Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .surveyId(question.getSurveyId())
                .questionText(question.getQuestionText())
                .position(question.getPosition())
                .build();
    }


}
