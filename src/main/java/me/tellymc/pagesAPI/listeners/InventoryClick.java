package me.tellymc.pagesAPI.listeners;

import me.tellymc.pagesAPI.managers.PageManager;
import me.tellymc.pagesAPI.objects.Page;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private final PageManager pageManager;

    public InventoryClick(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        if (!pageManager.containsPlayer(player)) return;
        if (event.getClickedInventory() == null) return;

        Page page = pageManager.getPlayer(player);

        if (!event.getView().getTopInventory().equals(page.getInventory())) return;

        event.setCancelled(true);

        page.handleClick(event);
    }
}