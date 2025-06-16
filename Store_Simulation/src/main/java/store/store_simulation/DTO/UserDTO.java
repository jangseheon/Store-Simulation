package store.store_simulation.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "id를 입력하세요")
    private String id;

    @NotBlank(message = "이름를 입력하세요")
    private String name;

    @NotBlank
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력하세요")
    private String confirmPassword;

    @NotNull
    private Boolean isManager;
}
