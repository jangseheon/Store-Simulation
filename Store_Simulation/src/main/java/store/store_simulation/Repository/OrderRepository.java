package store.store_simulation.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import store.store_simulation.Entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    @EntityGraph(attributePaths = "items")
    List<Order> findAll();

    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    Integer findTotalSales();
}

