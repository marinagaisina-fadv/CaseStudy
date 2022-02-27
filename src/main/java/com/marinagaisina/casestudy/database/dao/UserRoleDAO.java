package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
}
