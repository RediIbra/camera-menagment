package com.npa.getway.converters;

import com.npa.getway.dto.CenterDTO;
import com.npa.getway.model.Center;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CenterToCenterDTO implements Converter<Center, CenterDTO> {
    @Override
    public CenterDTO convert(Center source) {
        if (source != null){
            CenterDTO centerDTO = new CenterDTO();
            centerDTO.setId(source.getId());
            centerDTO.setContact(source.getContact());
            centerDTO.setLocation(source.getLocation());
            centerDTO.setDesc(source.getDesc());


            return centerDTO;
        }
        return null;
    }
}
