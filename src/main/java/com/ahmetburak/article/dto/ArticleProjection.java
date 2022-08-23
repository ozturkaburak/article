package com.ahmetburak.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
public interface ArticleProjection {

    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate getPublishDate();

    Long getCount();

}
