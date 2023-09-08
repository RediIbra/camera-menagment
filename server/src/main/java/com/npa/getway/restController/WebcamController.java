package com.npa.getway.restController;

import com.npa.getway.dto.WebcamDTO;
import com.npa.getway.services.WebcamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webcam")
public class WebcamController  {

    private final WebcamService webcamService;

    public WebcamController(WebcamService webcamService) {
        this.webcamService = webcamService;
    }

    @GetMapping("/{id}")
    public List<WebcamDTO> findByCenterId(@PathVariable String id){
        return webcamService.findWebcamByCenterId(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody WebcamDTO webcamDTO){
        webcamService.saveOrUpdate(webcamDTO);

    }
}
