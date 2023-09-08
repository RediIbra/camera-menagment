package com.npa.getway.converters;

import com.npa.getway.dto.CenterDTO;
import com.npa.getway.model.Center;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CenterDTOToCenter implements Converter<CenterDTO, Center> {
    @Override
    public Center convert(CenterDTO source) {
        if (source != null){
            Center center = new Center();
            if (source.getId() != null){
                center.setId(source.getId());
            }
            center.setContact(source.getContact());
            center.setLocation(source.getLocation());
            center.setDesc(source.getDesc());

            return center;
        }
        return null;
    }
}
