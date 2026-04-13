package food_ordering_app.service;

import food_ordering_app.entity.MenuItem;
import food_ordering_app.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAllMenuItem() {
        return this.menuItemRepository.findAllMenuItem();
    }

    public MenuItem getMenuItemById(UUID id) {
        return this.menuItemRepository.findMenuItemById(id);
    }

    public void createMenuItem(MenuItem menuItem) {
        this.menuItemRepository.save_update(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        this.menuItemRepository.save_update(menuItem);
    }

    public void deleteMenuItemById(UUID id) {
        this.menuItemRepository.deleteById(id);
    }
}