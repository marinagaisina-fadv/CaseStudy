package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entities.Parcel;
import com.marinagaisina.casestudy.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParcelDAO extends JpaRepository <Parcel, Long> {

    Parcel findById(@Param("id") Integer id);

    List<Parcel> findByCustomerId(@Param("id") Integer id);

    List<Parcel> findByItemId(@Param("id") Integer id);

    @Query(value = "select p.* from inventorydb.packages p left join inventorydb.customers c on p.customer_id=c.id where c.fullName like CONCAT('%',:nameLike,'%')", nativeQuery = true)
    List<Parcel> findAllParcelsOfCustomerNameLike(@Param("nameLike") String firstNameLike);
}
