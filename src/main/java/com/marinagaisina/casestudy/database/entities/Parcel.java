package com.marinagaisina.casestudy.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "packages")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "dimensions")
    private String dimensions;

    //Datetime when the package was packed
    @Column(name = "date_assembled")
    private Timestamp dateAssembled;

    @Column(name = "status")
    private String status;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="pallet_id")
    private Pallet pallet;

    /*@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "package_item",
            joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

//    @Column(name = "pallet_id")
//    private Pallet palletId;

//    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "item_customer",
//            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
//    )
//    private List<Item> items = new ArrayList<>();