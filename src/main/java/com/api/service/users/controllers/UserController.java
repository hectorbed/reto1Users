package com.api.service.users.controllers;

import com.api.service.users.entities.User;
import com.api.service.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User userCreated = this.userService.saveUser(user);
        return ResponseEntity.ok(userCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted.");
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User userDb = this.userService.updateUser(user);
        if(userDb == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }
}
