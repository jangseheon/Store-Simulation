package store.store_simulation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.store_simulation.Entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
