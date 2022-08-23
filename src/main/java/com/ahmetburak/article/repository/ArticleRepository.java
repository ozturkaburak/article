package com.ahmetburak.article.repository;

import com.ahmetburak.article.dto.ArticleProjection;
import com.ahmetburak.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select count(ae.id) as count ,ae.publishDate as publishDate from Article ae where ae.publishDate>=:startDate and ae.publishDate<=:endDate group by ae.publishDate")
    List<ArticleProjection> findLast7DaysStatistics(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Override
    Optional<Article> findById(Long aLong);
}
