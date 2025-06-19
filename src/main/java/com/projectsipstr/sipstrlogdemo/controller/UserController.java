package com.projectsipstr.sipstrlogdemo.controller;

import com.projectsipstr.sipstrlogdemo.entity.AppUser;
import com.projectsipstr.sipstrlogdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long id) {
        logger.info("Fetching user with id: {}", id);
        AppUser user = userService.getUserById(id);
        if (user != null) {
            logger.debug("AppUser found: {}", user);
            return ResponseEntity.ok(user);
        } else {
            logger.warn("AppUser with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        logger.info("Creating user: {}", user);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        logger.info("Updating user with id {}: {}", id, user);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
