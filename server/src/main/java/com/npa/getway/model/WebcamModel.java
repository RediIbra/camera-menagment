package com.npa.getway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebcamModel extends BaseEntity{

    @OneToMany(mappedBy = "webcamModel")
    private Set<WebcamType> webcamTypes;

    @OneToMany(mappedBy = "webcamModel")
    private Set<WebcamInteractionProtocol> interactionProtocols;

    @OneToOne
    private WebcamAccessCredential webcamAccessCredential;

    @OneToOne
    private Status status;
}
