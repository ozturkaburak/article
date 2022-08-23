package com.ahmetburak.article.repository;

import com.ahmetburak.article.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ahmetburakozturk on 18.08.2022
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserEntityByUsername(String username);
}
