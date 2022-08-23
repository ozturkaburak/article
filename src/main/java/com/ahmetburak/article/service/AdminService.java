package com.ahmetburak.article.service;

import com.ahmetburak.article.dto.ArticleProjection;
import com.ahmetburak.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ArticleRepository articleRepository;

    public List<ArticleProjection> findLast7DaysStatistics() {
        LocalDate now = LocalDate.now();
        return articleRepository.findLast7DaysStatistics(now.minusDays(7), now);
    }

}
