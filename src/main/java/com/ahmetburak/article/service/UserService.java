package com.ahmetburak.article.service;

import com.ahmetburak.article.dto.UserDTO;
import com.ahmetburak.article.entity.User;
import com.ahmetburak.article.mapper.UserMapper;
import com.ahmetburak.article.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDTO save(UserDTO userDTO) {
        User userEntity = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(userEntity);
        savedUser.setPassword(passwordEncoder.encode(savedUser.getPassword()));
        savedUser.getRoles().forEach(roleEntity -> roleEntity.setUserEntity(savedUser));
        return userMapper.toDTO(savedUser);
    }

}
