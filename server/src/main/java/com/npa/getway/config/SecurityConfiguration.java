package com.npa.getway.config;

import com.npa.getway.model.Role;
import com.npa.getway.model.Users;
import com.npa.getway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        Users user1 =   new Users("admin", "admin@mail.com", passwordEncoder().encode("admin"),
                true, true, true, true);
        Users user2 =   new Users("user", "user@mail.com", passwordEncoder().encode("user"),
                true, true, true, true);
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username(user1.getUsername())
                        .password(user1.getPassword())
                        .accountExpired(!user1.isAccountNonExpired())
                        .accountLocked(!user1.isAccountNonLocked())
                        .disabled(!user1.isEnabled())
                        .credentialsExpired(!user1.isCredentialsNonExpired())
                        .roles(Role.SUPER_USER.name())
                        .build(),

                User.builder()
                        .username(user2.getUsername())
                        .password(user2.getPassword())
                        .accountExpired(!user2.isAccountNonExpired())
                        .accountLocked(!user2.isAccountNonLocked())
                        .disabled(!user2.isEnabled())
                        .credentialsExpired(!user2.isCredentialsNonExpired())
                        .roles(Role.USER.name())
                        .build()

        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                ;


        return http.build();
    }

}
