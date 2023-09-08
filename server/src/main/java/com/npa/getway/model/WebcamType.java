package com.npa.getway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebcamType extends BaseEntity {

    private String desc;

    @ManyToOne
    @JoinColumn(name = "webcamModel_id", nullable = false)
    private WebcamModel webcamModel;


}
