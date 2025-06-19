package com.projectsipstr.sipstrlogdemo.service;

import com.projectsipstr.sipstrlogdemo.entity.User;
import com.projectsipstr.sipstrlogdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers() {
        logger.info("Service: Fetching all users from DB");
        List<User> users = userRepository.findAll();
        logger.debug("Service: Found {} users", users.size());
        return users;
    }

    public User getUserById(Long id) {
        logger.info("Service: Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    logger.debug("Service: Found user: {}", user);
                    return user;
                })
                .orElseGet(() -> {
                    logger.warn("Service: User with ID {} not found", id);
                    return null;
                });
    }

    public User createUser(User user) {
        logger.info("Service: Creating user: {}", user);
        User savedUser = userRepository.save(user);
        logger.debug("Service: User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    public User updateUser(Long id, User user) {
        logger.info("Service: Updating user with ID: {}", id);
        user.setId(id);
        User updatedUser = userRepository.save(user);
        logger.debug("Service: User updated: {}", updatedUser);
        return updatedUser;
    }

    public void deleteUser(Long id) {
        logger.info("Service: Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.debug("Service: User with ID {} deleted successfully", id);
    }
}
