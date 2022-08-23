package com.ahmetburak.article.service.unit;

import com.ahmetburak.article.dto.UserDTO;
import com.ahmetburak.article.entity.Role;
import com.ahmetburak.article.entity.User;
import com.ahmetburak.article.enumeration.RoleEnum;
import com.ahmetburak.article.mapper.UserMapper;
import com.ahmetburak.article.repository.UserRepository;
import com.ahmetburak.article.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ahmetburakozturk on 22.08.2022
 **/
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void testSave(){
        Role entity=new Role();
        entity.setName(RoleEnum.ROLE_USER);
        entity.setName(RoleEnum.ROLE_ADMIN);
        Set<Role> roles=new HashSet<>();
        roles.add(entity);
        User userEntity=new User();
        userEntity.setFullName("Ahmet Burak Ozturk");
        userEntity.setPassword("123");
        userEntity.setUsername("abo");
        userEntity.setRoles(roles);

        UserDTO userDTO= Mappers.getMapper(UserMapper.class).toDTO(userEntity);

        String password="eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJzdWIiOiJhYm8iLCJhdWQiOiJhcGkuYXJ0aWNsZS5jb20iLCJuYmYiOjE2NjExNTU1MTMsImlhdCI6MTY2MTE1NTUxMywiZXhwIjoxNjYxMjQxOTEzfQ.kUqUVPS58AqenC5oKNNe_EE7oPRKxR-PCjkJpN01MQ9oUIlaJXbP5B2qx3uJS-GCaMOx3jlAZpIKhyLEucZdgA";

        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toEntity(any(UserDTO.class))).thenReturn(userEntity);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);
        when(passwordEncoder.encode(anyString())).thenReturn(password);

        UserDTO savedUser = userService.save(userDTO);

        verify(userRepository, times(1)).save(userEntity);
        assertEquals(userDTO.getFullName(), savedUser.getFullName());
        assertEquals(userDTO.getPassword(), savedUser.getPassword());
        assertEquals(userDTO.getUsername(), savedUser.getUsername());
        assertEquals(userDTO.getRoles(), savedUser.getRoles());

    }
}
