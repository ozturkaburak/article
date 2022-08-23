package com.ahmetburak.article.service;

import com.ahmetburak.article.dto.ArticleDTO;
import com.ahmetburak.article.entity.Article;
import com.ahmetburak.article.mapper.ArticleMapper;
import com.ahmetburak.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleDTO save(ArticleDTO articleDTO) {
        Article articleEntity = articleMapper.toEntity(articleDTO);
        articleEntity = articleRepository.save(articleEntity);
        return articleMapper.toDTO(articleEntity);
    }

    public List<ArticleDTO> list(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<Article> pageArticle = articleRepository.findAll(paging);
        return pageArticle.isEmpty() ? Collections.emptyList() : pageArticle.getContent().stream().map(articleMapper::toDTO).toList();
    }
}
