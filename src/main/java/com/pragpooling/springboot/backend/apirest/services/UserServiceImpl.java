package com.pragpooling.springboot.backend.apirest.services;

import com.pragpooling.springboot.backend.apirest.dao.IUserDao;
import com.pragpooling.springboot.backend.apirest.entity.User;
import com.pragpooling.springboot.backend.apirest.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private Date s;
    private final IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>)  userDao.findAll();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.deleteById(id);

    }
}
