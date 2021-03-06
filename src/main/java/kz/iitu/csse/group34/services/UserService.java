package kz.iitu.csse.group34.services;

import kz.iitu.csse.group34.entities.Users;
import kz.iitu.csse.group34.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(s);
        User secUser = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getRoles());
        if(user.getIsActive()){
           secUser.eraseCredentials();
        }
        return secUser;

    }
}
