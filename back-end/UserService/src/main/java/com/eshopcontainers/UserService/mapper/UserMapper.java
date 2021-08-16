package com.eshopcontainers.UserService.mapper;

import com.eshopcontainers.UserService.dto.UserDTO;
import com.eshopcontainers.UserService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
