package store.store_simulation.Service;

import org.springframework.stereotype.Service;
import store.store_simulation.Repository.MenuRepository;
import store.store_simulation.Repository.OrderRepository;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(MenuRepository menuRepository, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Integer getTotalSales() {
        Integer total = orderRepository.findTotalSales();
        return total != null ? total : 0;
    }

}