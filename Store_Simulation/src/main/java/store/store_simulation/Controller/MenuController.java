package store.store_simulation.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.store_simulation.DTO.MenuDTO;
import store.store_simulation.DTO.MenuSalesDTO;
import store.store_simulation.Repository.MenuRepository;
import store.store_simulation.Repository.OrderItemRepository;
import store.store_simulation.Service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuRepository menuRepo;
    private final OrderItemRepository orderItemRepo;
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDTO> addMenu(@Valid @RequestBody MenuDTO dto) {
        MenuDTO saved = menuService.addMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        List<MenuDTO> list = menuService.getAllMenus();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMenu(
            @PathVariable Integer id,
            @RequestBody @Valid MenuDTO menuDto) {
        menuService.updateMenu(id, menuDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sales-summary")
    public List<MenuSalesDTO> salesSummary() {
        return orderItemRepo.findMenuSalesSummary().stream()
                .map(arr -> new MenuSalesDTO(
                        (String) arr[0],
                        ((Number) arr[1]).longValue()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable int id) {
        if (!menuRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
