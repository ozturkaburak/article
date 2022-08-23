package com.ahmetburak.article.service.unit;

import com.ahmetburak.article.dto.ArticleProjection;
import com.ahmetburak.article.repository.ArticleRepository;
import com.ahmetburak.article.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ahmetburakozturk on 22.08.2022
 **/
@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock
    ArticleRepository articleRepository;

    @Test
    void testFindLast7DaysStatistics() {
        ArticleProjection articleProjection = new ArticleProjection() {
            @Override
            public LocalDate getPublishDate() {
                return LocalDate.now();
            }

            @Override
            public Long getCount() {
                return 2L;
            }
        };
        List<ArticleProjection> articleProjections = new ArrayList<>();
        articleProjections.add(articleProjection);

        when(articleRepository.findLast7DaysStatistics(any(LocalDate.class), any(LocalDate.class))).thenReturn(articleProjections);

        List<ArticleProjection> last7DaysStatistics = adminService.findLast7DaysStatistics();

        verify(articleRepository, times(1)).findLast7DaysStatistics(any(LocalDate.class), any(LocalDate.class));
        assertEquals(1, last7DaysStatistics.size());
    }
}
