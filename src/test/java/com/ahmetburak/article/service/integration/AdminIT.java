package com.ahmetburak.article.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ahmetburakozturk on 23.08.2022
 **/
@SpringBootTest
@AutoConfigureMockMvc
class AdminIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "abo", roles = {"USER", "ADMIN"})
    void testFindLast7DaysStatistics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "abo")
    void testFindLast7DaysStatisticsWithoutAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


}
