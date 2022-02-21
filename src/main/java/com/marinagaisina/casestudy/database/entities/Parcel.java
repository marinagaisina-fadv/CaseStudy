package com.marinagaisina.casestudy.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="pallet_id")
    private Pallet pallet;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcel)) return false;
        Parcel aParcel = (Parcel) o;
        return getId().equals(aParcel.getId()) && getItem().equals(aParcel.getItem()) && getCustomer().equals(aParcel.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItem(), getCustomer());
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