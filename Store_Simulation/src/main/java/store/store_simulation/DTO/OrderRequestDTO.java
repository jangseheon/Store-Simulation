package store.store_simulation.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotEmpty(message = "최소 하나 이상의 주문 항목이 필요합니다")
    @Valid
    private List<Item> items;

    @NotNull(message = "결제수단을 선택해 주세요")
    private PaymentMethod paymentMethod;

    public enum PaymentMethod {
        CARD, CASH
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        @NotNull(message = "메뉴 ID는 필수입니다")
        private int menuId;

        @Positive(message = "수량은 1 이상이어야 합니다")
        private int quantity;
    }
}
