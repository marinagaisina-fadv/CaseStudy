package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item, Long> {

    Item findById(@Param("id") Integer id);
    List<Item> findItemsByNameContainsIgnoreCase(@Param("name") String name);
    List<Item> findItemsByAvailQuantityIsLessThan(@Param("number") Integer number);
    List<Item> findItemsByAvailQuantityIsOrderByName(@Param("number") Integer number);
    List<Item> findItemsByNameContainsIgnoreCaseOrderByName(@Param("name") String name);
}
