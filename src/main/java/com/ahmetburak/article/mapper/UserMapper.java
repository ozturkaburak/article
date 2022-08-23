package com.ahmetburak.article.mapper;

import com.ahmetburak.article.dto.UserDTO;
import com.ahmetburak.article.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
