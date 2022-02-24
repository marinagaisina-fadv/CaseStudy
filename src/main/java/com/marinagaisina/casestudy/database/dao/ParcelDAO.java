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

    @Query(value = "select p.* from inventorydb.packages p join inventorydb.package_item pi on p.id = pi.package_id " +
            "join inventorydb.items i on pi.item_id = i.id" +
            " where i.name like CONCAT('%',:nameLike,'%')", nativeQuery = true)
    List<Parcel> findParcelsByItemNameLike(@Param("nameLike") String id);

    @Query(value = "select p.* from inventorydb.packages p left join inventorydb.customers c on p.customer_id=c.id" +
            " where c.fullname like CONCAT('%',:nameLike,'%')", nativeQuery = true)
    List<Parcel> findAllParcelsOfCustomerNameLike(@Param("nameLike") String firstNameLike);
}

