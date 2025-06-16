package store.store_simulation.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import store.store_simulation.DTO.UserDTO;
import store.store_simulation.Entity.User;
import store.store_simulation.Repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(UserDTO dto) {
        if (userRepo.existsById(dto.getId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        User user = User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isManager(dto.getIsManager())
                .build();
        userRepo.save(user);
    }

}
