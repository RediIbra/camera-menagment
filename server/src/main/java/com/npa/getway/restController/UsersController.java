package com.npa.getway.restController;

import com.npa.getway.dto.UsersDTO;
import com.npa.getway.services.UsersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Secured("ROLE_SUPER_USER")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<UsersDTO> findAll(){
        return usersService.findAll();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody UsersDTO usersDTO){
        usersService.saveOrUpdate(usersDTO);
    }

    @PostMapping("/{id}")
    public void disableOrEnableUser(@PathVariable String id){
        usersService.disableOrEnableUser(id);
    }
}
