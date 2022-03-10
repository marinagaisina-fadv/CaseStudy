package com.marinagaisina.casestudy.database.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "items")
public class Item {
    @Id
    // this annotation is what tells hibernate that this variable is an auto
    // incremented primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "avail_quantity")
    private Integer availQuantity;

    @Column(name = "quantity_to_package")
    private Integer quantityToPackage;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "package_item",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "package_id", referencedColumnName = "id")
    )
    private List<Parcel> packages = new ArrayList<>();

    /*@OneToMany(mappedBy = "item", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Parcel> parcelLinks = new ArrayList<>();*/

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId().equals(item.getId()) && getName().equals(item.getName()) && Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
