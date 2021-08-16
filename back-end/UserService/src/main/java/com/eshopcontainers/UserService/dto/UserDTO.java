package com.eshopcontainers.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private boolean activate;
    private Date createdDate;
    private Date updateDate;
    private String phoneNumber;
}
