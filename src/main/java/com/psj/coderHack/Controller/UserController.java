package com.psj.coderHack.Controller;

import com.psj.coderHack.Entities.User;
import com.psj.coderHack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> registerUser(@RequestParam String userId, @RequestParam String username) {
        User createdUser = userService.registerUser(userId, username);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return userService.getUser(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore())); // Sort by score descending
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<User> updateScore(@PathVariable String userId, @RequestParam int score) {
        try {
            User updatedUser = userService.updateScore(userId, score);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Invalid score or other error
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
