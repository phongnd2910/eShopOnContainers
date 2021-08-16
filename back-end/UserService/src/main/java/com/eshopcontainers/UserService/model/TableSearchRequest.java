package com.eshopcontainers.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableSearchRequest {
    private List<ColumnSearchRequest> columnSearchRequests;

    private TablePagingRequest pagingRequest;

    private TableSortingRequest sortingRequest;
}
