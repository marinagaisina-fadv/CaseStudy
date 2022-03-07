package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
}
