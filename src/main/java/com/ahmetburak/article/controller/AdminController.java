package com.ahmetburak.article.controller;

import com.ahmetburak.article.dto.ArticleProjection;
import com.ahmetburak.article.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<ArticleProjection>> findLast7DaysStatistics() {
        List<ArticleProjection> last7DaysStatistics = adminService.findLast7DaysStatistics();
        return new ResponseEntity<>(last7DaysStatistics, HttpStatus.OK);
    }
}
