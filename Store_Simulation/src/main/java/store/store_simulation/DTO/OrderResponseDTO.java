package store.store_simulation.DTO;

import lombok.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private int orderId;
    private List<OrderRequestDTO.Item> items;
    private OrderRequestDTO.PaymentMethod paymentMethod;
    private int totalPrice;
    private int cookingTime;
}

