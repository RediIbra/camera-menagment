package com.npa.getway.dto;

import com.npa.getway.model.Webcam;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CenterDTO {
    private Long id;

    private String desc;

    private String contact;

    private String location;

}
