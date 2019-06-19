package com.spring.example.service;

import com.spring.example.exception.DuplicateUserNameException;
import com.spring.example.exception.ResourceNotFoundException;
import com.spring.example.model.User;
import com.spring.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    @Override
    public User findOne(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User createUser(User user) {
        if(user.getUserName() == null)
            throw new ResourceNotFoundException("Username is required", "Username", user.getUserName());

        if(user.getPassword() == null)
            throw new ResourceNotFoundException("Password is required", "Password", user.getPassword());

        User usr = userRepository.findByUserName(user.getUserName());
        if(usr != null)
            throw new DuplicateUserNameException("Username Already exists.");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
