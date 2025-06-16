package store.store_simulation.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import store.store_simulation.Entity.User;
import store.store_simulation.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        String role = user.getIsManager() ? "ROLE_MANAGER" : "ROLE_USER";
        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }
}
