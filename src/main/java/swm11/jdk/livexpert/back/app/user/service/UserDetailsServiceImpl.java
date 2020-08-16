package swm11.jdk.livexpert.back.app.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import swm11.jdk.livexpert.back.app.user.model.MyUserDetails;
import swm11.jdk.livexpert.back.app.user.repository.UserRepository;
import swm11.jdk.livexpert.back.exception.UserNotFoundException;

import java.util.Collections;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).map(u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getCode())))).orElseThrow(() -> new UserNotFoundException(email));
    }

}