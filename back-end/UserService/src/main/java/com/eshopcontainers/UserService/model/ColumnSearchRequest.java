package com.eshopcontainers.UserService.model;

import com.eshopcontainers.UserService.constant.DataBaseOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnSearchRequest {

    private String columnName;

    private DataBaseOperation operation;

    private String term;

    private Date termDate;

    private boolean isOrTerm;

    private List<?> list;

    public boolean getIsOrTerm() {
        return isOrTerm;
    }

    public void setOrTerm(boolean orTerm) {
        isOrTerm = orTerm;
    }
}
