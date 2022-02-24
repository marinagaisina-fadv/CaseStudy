package com.marinagaisina.casestudy.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    // this annotation is what tells hibernate that this variable is an auto
    // incremented primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "full_delivery_address")
    private String deliveryAddress;

//    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "item_customer",
//            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
//    )
//    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)//,
            //cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Parcel> parcelLinks = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId().equals(customer.getId()) && getFullName().equals(customer.getFullName()) && getDeliveryAddress().equals(customer.getDeliveryAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getDeliveryAddress());
    }
}
