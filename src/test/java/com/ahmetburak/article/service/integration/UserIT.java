package com.ahmetburak.article.service.integration;

import com.ahmetburak.article.dto.RoleDTO;
import com.ahmetburak.article.dto.UserDTO;
import com.ahmetburak.article.enumeration.RoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ahmetburakozturk on 23.08.2022
 **/
@SpringBootTest
@AutoConfigureMockMvc
class UserIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSave() throws Exception {

        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(RoleEnum.ROLE_USER);

        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setName(RoleEnum.ROLE_ADMIN);

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName("Test User Name Surname");
        userDTO.setUsername("test_user");
        userDTO.setPassword("123");
        userDTO.setRoles(roles);

        roles.add(roleDTO);
        roles.add(roleDTO1);

        ObjectMapper objectMapper = new ObjectMapper();
        String stringUserDTO = objectMapper.writeValueAsString(userDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/save")
                        .content(stringUserDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }


}
