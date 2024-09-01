package com.osswa.yayaLimitusTest.Controller;

import com.osswa.yayaLimitusTest.Services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SurveyWebController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys")
    //TODO: inspect Model
    public String listSurveys (Model model){
        model.addAttribute("surveys", surveyService.getSurveys());
        return "surveys";
    }

}
