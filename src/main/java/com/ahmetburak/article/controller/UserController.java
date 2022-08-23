package com.ahmetburak.article.controller;

import com.ahmetburak.article.dto.UserDTO;
import com.ahmetburak.article.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("save")
    public UserDTO save(@Valid @RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

}
