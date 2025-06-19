package com.projectsipstr.sipstrlogdemo.service;

import com.projectsipstr.sipstrlogdemo.entity.AppUser;
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

    public List<AppUser> getAllUsers() {
        logger.info("Service: Fetching all users from DB");
        List<AppUser> users = userRepository.findAll();
        logger.debug("Service: Found {} users", users.size());
        return users;
    }

    public AppUser getUserById(Long id) {
        logger.info("Service: Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    logger.debug("Service: Found user: {}", user);
                    return user;
                })
                .orElseGet(() -> {
                    logger.warn("Service: AppUser with ID {} not found", id);
                    return null;
                });
    }

    public AppUser createUser(AppUser user) {
        logger.info("Service: Creating user: {}", user);
        AppUser savedUser = userRepository.save(user);
        logger.debug("Service: AppUser created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    public AppUser updateUser(Long id, AppUser user) {
        logger.info("Service: Updating user with ID: {}", id);
        user.setId(id);
        AppUser updatedUser = userRepository.save(user);
        logger.debug("Service: AppUser updated: {}", updatedUser);
        return updatedUser;
    }

    public void deleteUser(Long id) {
        logger.info("Service: Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.debug("Service: AppUser with ID {} deleted successfully", id);
    }
}
