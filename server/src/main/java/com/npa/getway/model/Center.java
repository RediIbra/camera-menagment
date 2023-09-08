package com.npa.getway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "centers")
public class Center extends BaseEntity{
    private String desc;

    private String contact;

    private String location;

    @OneToMany(mappedBy = "center")
    private Set<Webcam> webcams = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "centers_plates",
    joinColumns = @JoinColumn(name = "center_id"),
    inverseJoinColumns = @JoinColumn(name = "plate_id"))

    private Set<Plate> centers_plates = new HashSet<>();
}
