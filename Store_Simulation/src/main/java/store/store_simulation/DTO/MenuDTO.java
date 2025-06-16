package store.store_simulation.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private int menuId;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String name;

    @Positive(message = "가격은 양수여야 합니다")
    private int price;

    @Positive(message = "조리 시간은 1초 이상이어야 합니다")
    private int makeTime;
}
