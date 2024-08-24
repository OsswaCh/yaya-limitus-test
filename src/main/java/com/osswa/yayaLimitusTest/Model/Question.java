package com.osswa.yayaLimitusTest.Model;
import com.toshiba.mwcloud.gs.RowKey;
import lombok.Data;

@Data
public class Question {
    @RowKey String Id;
    String surveyId;
    String questionText;
    Integer position;

}
