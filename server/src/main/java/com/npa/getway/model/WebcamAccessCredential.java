package com.npa.getway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Getter
@Setter
public class WebcamAccessCredential extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "webcam_id")
    private Webcam webcam;

}
