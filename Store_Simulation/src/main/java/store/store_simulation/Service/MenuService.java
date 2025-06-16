package store.store_simulation.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.store_simulation.DTO.MenuDTO;
import store.store_simulation.Entity.Menu;
import store.store_simulation.Repository.MenuRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuDTO addMenu(MenuDTO dto) {
        Menu menu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .makeTime(dto.getMakeTime())
                .build();
        Menu saved = menuRepository.save(menu);

        return MenuDTO.builder()
                .menuId(saved.getMenuId())
                .name(saved.getName())
                .price(saved.getPrice())
                .makeTime(saved.getMakeTime())
                .build();
    }

    @Transactional
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menu -> MenuDTO.builder()
                        .menuId(menu.getMenuId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .makeTime(menu.getMakeTime())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateMenu(int id, MenuDTO dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("메뉴가 없습니다. id=" + id));
        menu.setName(dto.getName());
        menu.setPrice(dto.getPrice());
        menu.setMakeTime(dto.getMakeTime());
    }
}

