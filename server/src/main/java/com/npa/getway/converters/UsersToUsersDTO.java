package com.npa.getway.converters;

import com.npa.getway.dto.UsersDTO;
import com.npa.getway.model.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsersToUsersDTO implements Converter<Users, UsersDTO> {

    @Override
    public UsersDTO convert(Users source) {

        if (source != null){
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setUserId(source.getId());
            usersDTO.setUsername(source.getUsername());
            usersDTO.setEmail(source.getEmail());
            usersDTO.setEnabled(source.isEnabled());
            return usersDTO;
        }
        return null;
    }
}
