package me.tellymc.pagesAPI.managers;

import me.tellymc.pagesAPI.objects.Page;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PageManager {

    private final HashMap<UUID, Page> pages = new HashMap<>();

    public void putPlayer(Player player, Page page) {
        pages.put(player.getUniqueId(), page);
    }

    public void putPlayer(UUID uuid, Page page) {
        pages.put(uuid, page);
    }

    public boolean containsPlayer(Player player) {
        return pages.containsKey(player.getUniqueId());
    }

    public boolean containsPlayer(UUID uuid) {
        return pages.containsKey(uuid);
    }

    public void removePlayer(Player player) {
        pages.remove(player.getUniqueId());
    }

    public void removePlayer(UUID uuid) {
        pages.remove(uuid);
    }

    public Page getPlayer(Player player) {
        return pages.get(player.getUniqueId());
    }

    public Page getPlayer(UUID uuid) {
        return pages.get(uuid);
    }
}