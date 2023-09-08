package com.npa.getway.converters;

import com.npa.getway.dto.WebcamDTO;
import com.npa.getway.model.Webcam;
import com.npa.getway.repositories.CenterRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WebcamDTOToWebcam implements Converter<WebcamDTO, Webcam> {
    private final CenterRepository centerRepository;

    public WebcamDTOToWebcam(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public Webcam convert(WebcamDTO source) {
        if (source != null){
            Webcam webcam = new Webcam();
            if (source.getId() != null){
                webcam.setId(source.getId());
                webcam.setDesc(source.getDesc());
                webcam.setCenter(centerRepository.findById(source.getCenterId()).get());
                return webcam;
            }
        }
        return null;
    }
}
