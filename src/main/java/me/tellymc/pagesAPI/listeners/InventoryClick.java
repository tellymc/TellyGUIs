package me.tellymc.pagesAPI.listeners;

import me.tellymc.pagesAPI.managers.PageManager;
import me.tellymc.pagesAPI.objects.Page;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {

    private final PageManager pageManager;

    public InventoryClick(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) return;

        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (inventory == null) return;
        if (event.getView().getTopInventory() != inventory) return;
        if (!pageManager.containsPlayer(player)) return;
        if (pageManager.getPlayer(player).getInventory() != event.getView().getTopInventory()) return;

        Page page = pageManager.getPlayer(player);

        event.setCancelled(true);
        page.handleClick(event);
    }
}