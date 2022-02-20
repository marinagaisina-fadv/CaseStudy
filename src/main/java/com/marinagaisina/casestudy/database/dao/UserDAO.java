package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.User;
import com.marinagaisina.casestudy.database.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    public User findById(@Param("id") Integer id);

    public User findByEmail(@Param("email") String email);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(Integer userId);
}

