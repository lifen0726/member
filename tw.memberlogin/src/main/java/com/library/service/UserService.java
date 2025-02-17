package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.exception.UserNotFoundException;
import com.library.repository.UserRepository; 
import com.library.model.Users; 

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired 
    private UserRepository userRepository; 
    
    public Users findByNameAndPsw(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users findByName(String username) {
        Optional<Users> uResp = userRepository.findByUserName(username);
        if(uResp.isEmpty()) {
            throw new UsernameNotFoundException("Can't find user."); 
        }
        return uResp.get();
    }

    public Users saveUser(Users user) {
        return userRepository.save(user); 
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username); 
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId); 
    }

    public Users getUserById(int userId) {
        Optional<Users> optionalUser = userRepository.findById(userId); 

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId); 
        }
    }
}
