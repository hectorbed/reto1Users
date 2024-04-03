package com.api.service.users.services;

import com.api.service.users.entities.User;
import com.api.service.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public User updateUser(User user){
        User userDb = this.userRepository.findById(user.getId()).orElse(null);
        if(userDb == null){
            return null;
        }
        userDb.setName(user.getName());
        return this.userRepository.save(userDb);
    }
}
