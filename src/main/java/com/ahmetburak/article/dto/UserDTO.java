package com.ahmetburak.article.dto;

import com.ahmetburak.article.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
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
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    private Set<RoleDTO> roles;

    public UserDTO() {
        this.roles = new HashSet<>();
    }

    public void addRole(RoleDTO roleDTO) {
        if (Objects.isNull(roleDTO)) {
            return;
        }
        //if role map is empty or roleDTO is not in the list, add the roleDTO into the role map
        if (this.roles.isEmpty() || this.roles.stream().noneMatch(roleDTO1 -> roleDTO1.getName().equals(roleDTO.getName()))) {
            this.roles.add(roleDTO);
        }

    }
}
