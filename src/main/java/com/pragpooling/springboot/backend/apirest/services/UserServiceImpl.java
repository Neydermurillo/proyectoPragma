package com.pragpooling.springboot.backend.apirest.services;

import com.pragpooling.springboot.backend.apirest.dao.IUserDao;
import com.pragpooling.springboot.backend.apirest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>)  userDao.findAll();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
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
