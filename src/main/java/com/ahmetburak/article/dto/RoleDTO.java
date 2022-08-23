package com.ahmetburak.article.dto;

import com.ahmetburak.article.entity.Base;
import com.ahmetburak.article.enumeration.RoleEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
@Data
public class RoleDTO implements Base {

    private Long id;

    @NotNull
    private RoleEnum name;
}
