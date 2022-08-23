package com.ahmetburak.article.dto;

import com.ahmetburak.article.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Getter
@Setter
public class UserDTO implements Base {

    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Set<RoleDTO> roles;

    public UserDTO() {
        this.roles = new HashSet<>();
    }

}
