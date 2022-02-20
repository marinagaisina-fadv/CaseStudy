package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.User;
import com.marinagaisina.casestudy.database.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(@Param("id") Integer id);

    User findByEmail(@Param("email") String email);

    @Query("select u from User u where u.firstName = :somename")
    public List<User> findByFirstName(@Param("somename") String firstName);

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(Integer userId);

    @Query(value = "select u.* from users u where u.first_name like CONCAT('%',:firstNameLike,'%')", nativeQuery = true)
    List<User> findByFirstNameLike(@Param("firstNameLike") String firstNameLike);

    @Query(value = "SELECT * FROM Users u WHERE u.username = ?1", nativeQuery = true)
    User findUserByUsernameNative(Integer status);

    @Query("SELECT u FROM User u WHERE u.email = ?1 and u.lastName = ?2")
    User findUserByStatusAndName(String email, String lastName);
}

