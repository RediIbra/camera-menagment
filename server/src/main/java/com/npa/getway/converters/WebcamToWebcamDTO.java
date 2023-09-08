package com.npa.getway.converters;

import com.npa.getway.dto.WebcamDTO;
import com.npa.getway.model.Webcam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WebcamToWebcamDTO implements Converter<Webcam, WebcamDTO> {
    @Override
    public WebcamDTO convert(Webcam source) {
        if (source != null){
            WebcamDTO webcamDTO = new WebcamDTO();
            webcamDTO.setId(source.getId());
            webcamDTO.setDesc(source.getDesc());
            webcamDTO.setCenterId(source.getCenter().getId());
            return webcamDTO;
        }

        return null;
    }
}
