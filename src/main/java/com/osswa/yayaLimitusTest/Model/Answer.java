package com.osswa.yayaLimitusTest.Model;
import com.toshiba.mwcloud.gs.RowKey;
import java.util.Date;


import lombok.Data;

@Data
public class Answer {
    @RowKey String Id;
    String surveyResponseId;
    String questionId;
    Date createdAt;
    String answer;
}
