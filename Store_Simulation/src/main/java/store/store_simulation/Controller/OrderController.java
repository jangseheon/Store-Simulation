package store.store_simulation.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.store_simulation.DTO.OrderRequestDTO;
import store.store_simulation.Entity.Menu;
import store.store_simulation.Entity.Order;
import store.store_simulation.Entity.OrderItem;
import store.store_simulation.Repository.MenuRepository;
import store.store_simulation.Repository.OrderRepository;
import store.store_simulation.Service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final MenuRepository menuRepo;
    private final OrderRepository orderRepo;
    private final OrderService orderService;

    @PostMapping("/api/orders")
    @ResponseBody
    @Transactional
    public int createOrder(@RequestBody @Valid OrderRequestDTO req) {
        Order order = Order.builder()
                .paymentMethod(req.getPaymentMethod().name())
                .build();
        int totalPrice = 0;
        for (var item : req.getItems()) {
            Menu menu = menuRepo.findById(item.getMenuId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("메뉴 not found: " + item.getMenuId()));
            totalPrice += menu.getPrice() * item.getQuantity();
            OrderItem oi = OrderItem.builder()
                    .menuId(menu.getMenuId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .quantity(item.getQuantity())
                    .build();
            oi.setOrder(order);
            order.getItems().add(oi);
        }
        order.setTotalPrice(totalPrice);
        orderRepo.save(order);
        return order.getOrderId();
    }

    @DeleteMapping("/api/cart/clear")
    @ResponseBody
    public ResponseEntity<String> clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return ResponseEntity.ok("장바구니가 초기화되었습니다.");
    }

    @GetMapping("/")
    public String home(Model model) {
        int totalSales = orderService.getTotalSales();
        model.addAttribute("totalSales", totalSales);

        return "home";
    }

    @GetMapping("/api/orders/total-sales")
    @ResponseBody
    public int getTotalSalesApi() {
        return orderService.getTotalSales();
    }
}
