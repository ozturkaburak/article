package com.ahmetburak.article.controller;

import com.ahmetburak.article.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<HashMap<String, String>> login(@RequestParam String username, @RequestParam String password) {
        String accessToken = authService.authenticate(username, password);
        HashMap<String, String> responseHashMap = createResponseMap(accessToken);
        return new ResponseEntity<>(responseHashMap, HttpStatus.OK);
    }

    private HashMap<String, String> createResponseMap(String accessToken) {
        HashMap<String, String> responseHashMap = new HashMap<>();
        responseHashMap.put("accessToken", accessToken);
        return responseHashMap;
    }
}
