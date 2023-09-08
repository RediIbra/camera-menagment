package com.npa.getway.services;

import com.npa.getway.converters.WebcamDTOToWebcam;
import com.npa.getway.converters.WebcamToWebcamDTO;
import com.npa.getway.dto.WebcamDTO;
import com.npa.getway.model.Webcam;
import com.npa.getway.repositories.WebcamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebcamService {

    private final WebcamRepository webcamRepository;
    private final WebcamToWebcamDTO webcamToWebcamDTO;

    private final WebcamDTOToWebcam webcamDTOToWebcam;

    public WebcamService(WebcamRepository webcamRepository, WebcamToWebcamDTO webcamToWebcamDTO, WebcamDTOToWebcam webcamDTOToWebcam) {
        this.webcamRepository = webcamRepository;
        this.webcamToWebcamDTO = webcamToWebcamDTO;
        this.webcamDTOToWebcam = webcamDTOToWebcam;
    }

    public List<WebcamDTO> findWebcamByCenterId(String id){
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Webcam id: \"" + id + "\" can't be parsed!");
        }
        return webcamRepository.findByCenterId(parseId).stream().map(webcam -> webcamToWebcamDTO.convert(webcam)).collect(Collectors.toList());
    }

    public void saveOrUpdate(WebcamDTO webcamDTO){
        webcamRepository.save(webcamDTOToWebcam.convert(webcamDTO));
    }
}
