package com.npa.getway.converters;

import com.npa.getway.dto.UsersDTO;
import com.npa.getway.model.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersDTOToUsers implements Converter<UsersDTO, Users> {

    private final PasswordEncoder passwordEncoder;

    public UsersDTOToUsers(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users convert(UsersDTO source) {
        if (source != null){
            Users users = new Users();
            if (source.getUserId() != null){
                users.setId(users.getId());
            }else {
                users.setPassword(passwordEncoder.encode(source.getPassword()));
            }
            users.setUsername(source.getUsername());
            users.setEnabled(users.isEnabled());
            users.setEmail(source.getEmail());

            return users;
        }
        return null;
    }
}
