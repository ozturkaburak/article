package com.ahmetburak.article.service.unit;

import com.ahmetburak.article.dto.ArticleDTO;
import com.ahmetburak.article.entity.Article;
import com.ahmetburak.article.mapper.ArticleMapper;
import com.ahmetburak.article.repository.ArticleRepository;
import com.ahmetburak.article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by ahmetburakozturk on 22.08.2022
 **/
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    public static final String AUTHOR_NAME = "Ahmet Burak Ozturk";
    public static final String TEST_TITLE = "Test Article Title";
    public static final String TEST_CONTENT = "Bla bla bla Test Content";

    @InjectMocks
    ArticleService articleService;

    @Spy
    ArticleRepository articleRepository;

    @Mock
    ArticleMapper articleMapper;


//    @Test
    void testSave() {

        ArticleDTO dto = new ArticleDTO();
        dto.setAuthor(AUTHOR_NAME);
        dto.setTitle(TEST_TITLE);
        dto.setContent(TEST_CONTENT);

        Article articleEntity = new Article();
        articleEntity.setAuthor(AUTHOR_NAME);
        articleEntity.setTitle(TEST_TITLE);
        articleEntity.setContent(TEST_CONTENT);

        when(articleMapper.toEntity(any(ArticleDTO.class))).thenReturn(articleEntity);
        when(articleRepository.save(any(Article.class))).thenReturn(articleEntity);
        when(articleMapper.toDTO(any(Article.class))).thenReturn(dto);

        ArticleDTO savedArticle = articleService.save(dto);

        verify(articleRepository, times(1)).save(articleEntity);
        assertEquals(dto.getAuthor(), savedArticle.getAuthor());
        assertEquals(dto.getTitle(), savedArticle.getTitle());
        assertEquals(dto.getContent(), savedArticle.getContent());

    }

    @Test
    void testList() {
        Page<Article> pageArticle = Mockito.mock(Page.class);
        Pageable paging = PageRequest.of(0, 5, Sort.by("id").descending());

        when(articleRepository.findAll(any(Pageable.class))).thenReturn(pageArticle);

        articleService.list(0, 5);

        verify(articleRepository, times(1)).findAll(paging);
    }


}
