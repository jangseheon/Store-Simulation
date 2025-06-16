package store.store_simulation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import store.store_simulation.Entity.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT oi.name, SUM(oi.quantity) FROM OrderItem oi GROUP BY oi.name")
    List<Object[]> findMenuSalesSummary();
}
