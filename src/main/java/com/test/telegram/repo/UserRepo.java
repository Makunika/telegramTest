package com.test.telegram.repo;

import com.test.telegram.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByUserTgId(Integer userTgId);
}
