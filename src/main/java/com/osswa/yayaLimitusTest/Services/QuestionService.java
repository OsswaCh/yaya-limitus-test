package com.osswa.yayaLimitusTest.Services;


import com.osswa.yayaLimitusTest.DTO.QuestionDto;
import com.osswa.yayaLimitusTest.Model.Question;
import com.toshiba.mwcloud.gs.GSException;
import com.toshiba.mwcloud.gs.NotNull;
import com.toshiba.mwcloud.gs.Query;
import com.toshiba.mwcloud.gs.RowSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    @Autowired
    private com.toshiba.mwcloud.gs.Collection<String, Question> questionCollection;

    //Fetches all questions related to a given survey ID.
    public List<QuestionDto> getQuestions(String surveyId){

        String tql = String.format("SELECT * FROM questions WHERE survey_id = '%s'", surveyId);
        List<QuestionDto> questionDtos = new ArrayList<>();

        try{
            Query<Question> query = questionCollection.query(tql);
            RowSet<Question> rs = query.fetch();

            while (rs.hasNext()){
                Question question = rs.next();
                questionDtos.add(mapToDto(question));
            }
        } catch (GSException e){
            logger.error("Error fetching questions for survey ID: {}", surveyId, e);
            throw new RuntimeException("Failed to fetch questions", e);
        }
        return questionDtos;
    }

    //Maps a Question entity to a QuestionDto.
    @NotNull
    private QuestionDto mapToDto(@org.jetbrains.annotations.NotNull Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .surveyId(question.getSurveyId())
                .questionText(question.getQuestionText())
                .position(question.getPosition())
                .build();
    }

}
