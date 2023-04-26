package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sqlinvestigation.RestAPI.models.userDB.User;
import ru.sqlinvestigation.RestAPI.repositories.userDB.UserRepository;
import ru.sqlinvestigation.RestAPI.models.userDB.UserDetails;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> person = userRepository.findByUsername(s);
        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new UserDetails(person.get());
    }

}
