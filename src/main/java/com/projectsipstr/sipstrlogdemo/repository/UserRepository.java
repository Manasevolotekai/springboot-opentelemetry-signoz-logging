package com.projectsipstr.sipstrlogdemo.repository;

import com.projectsipstr.sipstrlogdemo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
