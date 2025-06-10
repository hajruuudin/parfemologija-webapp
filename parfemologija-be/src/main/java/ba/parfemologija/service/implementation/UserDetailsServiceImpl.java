package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<String> roles = new ArrayList<>();
        if (user.getIsAdmin()) {
            roles.add("ADMIN");
        } else {
            roles.add("USER");
        }

        String detailUsername = user.getUsername();
        String detailPassword = user.getPassword();

        return new User(
                detailUsername,
                detailPassword,
                roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));
    }
}