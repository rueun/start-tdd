package com.example.starttdd.chapter7.testdouble;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String id);
}
