package luis122448.projectpizza.service;

import luis122448.projectpizza.persistence.entity.UserEntity;
import luis122448.projectpizza.persistence.entity.UserRoleEntity;
import luis122448.projectpizza.persistence.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User + " + username + " not found"));
        String [] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        UserDetails userDetails =  User.builder()
                            .username(userEntity.getUsername())
                            .password(userEntity.getPassword())
//                            .roles(roles)
                            .authorities(this.grantedAuthorities(roles))
                            .accountLocked(userEntity.getLocked())
                            .disabled(userEntity.getDisabled())
                            .build();
        return userDetails;
    }

    private String[] getAuthorities(String role){
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"random_order"};
        }
        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthorities(String [] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String rol:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+rol));
            for (String authority: this.getAuthorities(rol)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
