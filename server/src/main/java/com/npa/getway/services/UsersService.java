package com.npa.getway.services;

import com.npa.getway.converters.UsersDTOToUsers;
import com.npa.getway.converters.UsersToUsersDTO;
import com.npa.getway.dto.UsersDTO;
import com.npa.getway.model.Role;
import com.npa.getway.model.Users;
import com.npa.getway.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final UsersRepository usersRepository;
    private final UsersToUsersDTO usersToUsersDTO;
    private final UsersDTOToUsers usersDTOToUsers;

    public UsersService(UsersRepository usersRepository, UsersToUsersDTO usersToUsersDTO, UsersDTOToUsers usersDTOToUsers,  InMemoryUserDetailsManager userDetailsManager) {
        this.usersRepository = usersRepository;
        this.usersToUsersDTO = usersToUsersDTO;
        this.usersDTOToUsers = usersDTOToUsers;
        this.userDetailsManager = userDetailsManager;
    }

    public List<UsersDTO> findAll(){
        return usersRepository.findAll().stream().map(users -> usersToUsersDTO.convert(users)).collect(Collectors.toList());
    }

    public void saveOrUpdate(UsersDTO usersDTO){
        Users users = usersRepository.save(usersDTOToUsers.convert(usersDTO));
        if (users != null){
            userDetailsManager.createUser(User.builder()
                    .username(users.getUsername())
                    .password(users.getPassword())
                    .accountExpired(!users.isAccountNonExpired())
                    .accountLocked(!users.isAccountNonLocked())
                    .disabled(!users.isEnabled())
                    .credentialsExpired(!users.isAccountNonExpired())
                    .roles(Role.SUPER_USER.name())
                    .build());
        }
    }

    public void disableOrEnableUser(String id) {
        Users users = usersRepository.findById(Long.parseLong(id)).
                orElseThrow(() -> new UsernameNotFoundException("Username with id: " + id + " doesn't exist!"));
        users.setEnabled(!users.isEnabled());
        Users updatedUser = usersRepository.save(users);
        if (updatedUser != null) {
            userDetailsManager.updateUser(User.builder()
                    .username(updatedUser.getUsername())
                    .password(updatedUser.getPassword())
                    .accountExpired(!updatedUser.isAccountNonExpired())
                    .accountLocked(!updatedUser.isAccountNonLocked())
                    .disabled(!updatedUser.isEnabled())
                    .credentialsExpired(!updatedUser.isAccountNonExpired())
                    .roles(Role.SUPER_USER.name())
                    .build());
        }
    }
}
