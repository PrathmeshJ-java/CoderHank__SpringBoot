package com.psj.coderHack.Service;

import com.psj.coderHack.Entities.User;
import com.psj.coderHack.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String userId, String username) {
        if (userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User already exists");
        }
        User newUser = new User(userId, username);
        return userRepository.save(newUser);
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateScore(String userId, int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Invalid score");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setScore(score);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("User not found");
    }

    public void deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
