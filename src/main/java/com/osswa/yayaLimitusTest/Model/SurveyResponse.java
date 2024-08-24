package com.osswa.yayaLimitusTest.Model;

import com.toshiba.mwcloud.gs.RowKey;
import lombok.Data;

import java.util.Date;

@Data
public class SurveyResponse {
    @RowKey
    String id;
    String respondentId;
    String surveyId;
    Date startedAT;
    Date completedAt;

}
