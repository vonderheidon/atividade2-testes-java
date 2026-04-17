package com.app.auth.dao;

import com.app.auth.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAO {
    private static final List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(UUID id) {
        return users.removeIf(u -> u.getId().equals(id));
    }

}