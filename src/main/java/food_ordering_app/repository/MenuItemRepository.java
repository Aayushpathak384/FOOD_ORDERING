package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.MenuItem;
import food_ordering_app.repository.JPA.MenuItemJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MenuItemRepository {
    private final MenuItemJPARepository menuItemJPARepository;

    @Autowired
    public MenuItemRepository(MenuItemJPARepository menuItemJPARepository) {
        this.menuItemJPARepository = menuItemJPARepository;
    }

    public List<MenuItem> findAllMenuItem() {
        return this.menuItemJPARepository.findAll();
    }

    public MenuItem findMenuItemById(UUID uuid) {
        return this.menuItemJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(MenuItem.class, "uuid", uuid));
    }

    public void save_update(MenuItem menuItem) {
        this.menuItemJPARepository.save(menuItem);
    }

    public void deleteById(UUID uuid) {
        this.menuItemJPARepository.deleteById(uuid);
    }
}
