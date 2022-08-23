package com.ahmetburak.article.service;

import com.ahmetburak.article.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
public class UserDetailsImpl implements UserDetails {

    private final User userEntity;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User userEntity) {
        this.userEntity = userEntity;
        this.authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(user.getUsername(), this.userEntity.getUsername());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
