package com.ahmetburak.article.controller;

import com.ahmetburak.article.dto.ArticleDTO;
import com.ahmetburak.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleDTO save(@Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.save(articleDTO);
    }

    @GetMapping
    public List<ArticleDTO> list(@RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        return articleService.list(pageNo, pageSize);
    }
}
