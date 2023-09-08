package com.npa.getway.services;

import com.npa.getway.converters.CenterDTOToCenter;
import com.npa.getway.converters.CenterToCenterDTO;
import com.npa.getway.dto.CenterDTO;
import com.npa.getway.exceptions.NotFoundException;
import com.npa.getway.repositories.CenterRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CenterService {

    private final CenterRepository centerRepository;
    private final CenterToCenterDTO centerToCenterDTO;
    private final CenterDTOToCenter centerDTOToCenter;

    public CenterService(CenterRepository centerRepository, CenterToCenterDTO centerToCenterDTO, CenterDTOToCenter centerDTOToCenter) {
        this.centerRepository = centerRepository;
        this.centerToCenterDTO = centerToCenterDTO;
        this.centerDTOToCenter = centerDTOToCenter;
    }

    public CenterDTO findById(String id){
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Center id: \"" + id + "\" can't be parsed!");
        }

        return centerToCenterDTO.convert(centerRepository.findById(parseId).orElseThrow(()-> new NotFoundException("Record with id: " + id + " not found!")));
    }

    public List<CenterDTO> findAll(String pageNumber){
        return pageNumber == null ? centerRepository.findAll()
                .stream().map(center -> centerToCenterDTO.convert(center)).collect(Collectors.toList()) :
                centerRepository.findAll(PageRequest.of(Integer.parseInt(pageNumber), 2))
                .stream().map(center -> centerToCenterDTO.convert(center)).collect(Collectors.toList());
    }

    public void saveOrUpdate(CenterDTO centerDTO){
        centerRepository.save(centerDTOToCenter.convert(centerDTO));
    }


    public void deleteCenterById(String id){
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Center id: \"" + id + "\" can't be parsed!");
        }
        centerRepository.deleteById(parseId);
    }

}
