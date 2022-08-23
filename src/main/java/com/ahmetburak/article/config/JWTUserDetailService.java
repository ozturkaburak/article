package com.ahmetburak.article.config;

import com.ahmetburak.article.entity.User;
import com.ahmetburak.article.exception.ObjectNotFoundException;
import com.ahmetburak.article.repository.UserRepository;
import com.ahmetburak.article.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/

@Service
@Transactional
@RequiredArgsConstructor
public class JWTUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userEntity = userRepository.findUserEntityByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found with username: " + username));
        return new UserDetailsImpl(userEntity);
    }
}
