package com.marinagaisina.casestudy.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pallets")
public class Pallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "barcode")
    private String barcode;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="location_created")
    private Location locationCreated;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_destination")
    private Location locationDestination;

    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Column(name = "date_pickedup")
    private Timestamp datePickedUp;

    @Column(name = "date_delivered")
    private Timestamp dateDelivered;

    @Column(name = "status_will_be_generated")
    private String status;

    @OneToMany(mappedBy = "pallet", fetch = FetchType.LAZY)//,
            //cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Parcel> packagesList = new ArrayList<>();


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
