package me.tellymc.pagesAPI.listeners;

import me.tellymc.pagesAPI.managers.PageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    private final PageManager pageManager;

    public LeaveListener(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        pageManager.removePlayer(player);
    }
}