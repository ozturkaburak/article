package com.ahmetburak.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Data
public class ArticleDTO {
    private Long id;

    @NotNull
    private String author;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull
    private LocalDate publishDate;
}
