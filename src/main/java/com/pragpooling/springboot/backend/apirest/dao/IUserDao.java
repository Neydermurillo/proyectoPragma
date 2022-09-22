package com.pragpooling.springboot.backend.apirest.dao;

import com.pragpooling.springboot.backend.apirest.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {
}
