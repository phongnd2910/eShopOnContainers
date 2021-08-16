package com.eshopcontainers.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TablePagingRequest {

    private Integer page;
    private Integer pageSize;
}
