package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.User;
import com.marinagaisina.casestudy.database.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository - instead of extending JpaRepository, can use annotation @Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findById(@Param("id") Integer id);

    User findByEmail(@Param("email") String email);

    @Query("select u from User u where u.firstName = :somename")
    public List<User> findByFirstName(@Param("somename") String firstName);

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(Integer userId);

    @Query(value = "select u.* from users u where LOWER(u.first_name) like CONCAT('%',:firstNameLike,'%') or LOWER(u.last_name) like CONCAT('%',:lastNameLike,'%')", nativeQuery = true)
    List<User> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(@Param("firstNameLike") String firstNameLike, @Param("lastNameLike") String lastNameLike);

    @Query(value = "SELECT * FROM Users u WHERE u.username = ?1", nativeQuery = true)
    User findUserByUsernameNative(Integer status);

    @Query("SELECT u FROM User u WHERE u.email = ?1 and u.lastName = ?2")
    User findUserByEmailAndLastName(String email, String lastName);
}

