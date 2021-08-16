package com.eshopcontainers.UserService.model;

import com.eshopcontainers.UserService.constant.DataBaseDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableSortingRequest {

    private String columnName;
    private DataBaseDirection direction;
}