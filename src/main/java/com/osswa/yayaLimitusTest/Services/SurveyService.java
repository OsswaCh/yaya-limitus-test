package com.osswa.yayaLimitusTest.Services;


import com.osswa.yayaLimitusTest.DTO.SurveyCreateRequest;
import com.osswa.yayaLimitusTest.Model.Survey;
import com.toshiba.mwcloud.gs.Collection;
import com.toshiba.mwcloud.gs.GSException;
import com.toshiba.mwcloud.gs.RowSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toshiba.mwcloud.gs.Query;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.KeyGenerator;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j

// receives data from a web request (SurveyWebController) and then assigns the value to a new Survey entity.
public class SurveyService {

    @Autowired
    private Collection<String, Survey> surveyCollection;

    @Autowired
    private QuestionService questionService;

    public String create (SurveyCreateRequest request){

        Survey survey = new Survey();
        survey.setId(KeyGenerator.next("survey"));
        survey.set_active(request.isActive());
        survey.setTitle(request.getTitle());
        survey.setDescription(request.getDescription());
        try {
            log.info("pit: {}", survey);
            surveyCollection.put(survey.getId(), survey);
        }
        catch (GSException e){
            //TODO: make better
            e.printStackTrace();
        }

        return survey.getId();

    }

    public SurveyDto getSurvey (String surveyId){
        String tql = String.format("select * from survey where id='%s'", surveyId);
        List<SurveyDto> surveyDtos = Query(tql, true);
        if (surveyDtos == null || surveyDtos.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Not Found");
        }
        return surveyDtos.getFirst();
    }

    public List<SurveyDto> getSurveys (){
        String tql = "select * from surveys limit 50";
        return query(tql, false);
    }

    private List<SurveyDto> query(String tql, boolean includeQuestion){
        List<SurveyDto> result = new ArrayList<>().reversed();
        Query<Survey> query;
        try {
            query = surveyCollection.query(tql);
            RowSet<Survey> rs = query.fetch();
            while (rs.hasNext()){
                Survey model = rs.next();
                List<QuestionSto> questions = new ArrayList<>();
                if (includeQuestion) questions = questionService.getQuestions(model.getId());
                result.add(
                        SurveyDto.builder()
                                .id(model.getId())
                                .createdAt(model.getCreatedAt())
                                .description(model.getDescription())
                                .title(model.getTitle())
                                .isActive(model.is_active())
                                .questions(questions)
                                .build()
                );
            }

        } catch (GSException e){
            e.printStackTrace();
        }
        return result;
    }

}
