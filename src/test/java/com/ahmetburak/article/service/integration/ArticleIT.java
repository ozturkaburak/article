package com.ahmetburak.article.service.integration;

import com.ahmetburak.article.dto.ArticleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ahmetburakozturk on 23.08.2022
 **/
@SpringBootTest
@AutoConfigureMockMvc
class ArticleIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSave() throws Exception {

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("Test Title");
        articleDTO.setAuthor("Test Author Name Surname");
        articleDTO.setContent("Test Content");
        articleDTO.setPublishDate(LocalDate.now());


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String stringArticleDTO = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .with(user("test_user"))
                        .with(csrf())
                        .content(stringArticleDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles")
                        .with(user("test_user"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
