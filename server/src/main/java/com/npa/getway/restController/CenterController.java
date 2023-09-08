package com.npa.getway.restController;

import com.npa.getway.dto.CenterDTO;
import com.npa.getway.services.CenterService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
@Secured({"ROLE_SUPER_USER", "ROLE_USER"})
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping({"","/"})
    public List<CenterDTO> findAll(@RequestParam(required = false) String pageNumber){
        return centerService.findAll(pageNumber);
    }


    @GetMapping("/{id}")
    public CenterDTO findCenterById(@PathVariable String id){
        return centerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCenter(@PathVariable String id){
        centerService.deleteCenterById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody CenterDTO centerDTO){
        centerService.saveOrUpdate(centerDTO);
    }

}
