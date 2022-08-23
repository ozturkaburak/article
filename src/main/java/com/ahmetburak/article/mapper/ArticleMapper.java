package com.ahmetburak.article.mapper;

import com.ahmetburak.article.dto.ArticleDTO;
import com.ahmetburak.article.entity.Article;
import org.mapstruct.Mapper;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDTO toDTO(Article article);

    Article toEntity(ArticleDTO articleDTO);

}
