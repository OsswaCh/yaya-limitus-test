package com.osswa.yayaLimitusTest.Model;
import com.toshiba.mwcloud.gs.RowKey;
import lombok.Data;

@Data
public class Respondent {
    @RowKey
    String id;
}
