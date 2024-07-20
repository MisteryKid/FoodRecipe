package com.example.food.service;

import com.example.food.domain.entity.User;
import com.example.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void writeUser(User user){

        userRepository.save(user);
    }

    public List<User> userList(){
        return userRepository.findAll();
    }
}
