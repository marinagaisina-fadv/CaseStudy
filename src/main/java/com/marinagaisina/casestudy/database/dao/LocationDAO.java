package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.Location;
import com.marinagaisina.casestudy.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LocationDAO extends JpaRepository<Location, Long> {
    Location findById(@Param("id") Integer id);
}
