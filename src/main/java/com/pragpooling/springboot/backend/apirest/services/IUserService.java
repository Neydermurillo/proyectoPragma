package com.pragpooling.springboot.backend.apirest.services;

import com.pragpooling.springboot.backend.apirest.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();
    public User findById(Long id);
    public User save(User user);
    public void delete(Long id);
}
