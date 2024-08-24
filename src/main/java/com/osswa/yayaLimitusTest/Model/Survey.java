package com.osswa.yayaLimitusTest.Model;
import com.toshiba.mwcloud.gs.RowKey;
import lombok.Data;

import java.util.Date;


@Data
public class Survey {
    @RowKey
    String id;
    String title;
    String description;
    boolean is_active;
    Date createdAt;
}
