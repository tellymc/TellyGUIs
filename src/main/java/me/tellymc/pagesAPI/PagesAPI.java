package me.tellymc.pagesAPI;

import me.tellymc.pagesAPI.listeners.InventoryClick;
import me.tellymc.pagesAPI.listeners.LeaveListener;
import me.tellymc.pagesAPI.managers.PageManager;
import me.tellymc.pagesAPI.objects.Page;
import org.bukkit.plugin.java.JavaPlugin;

public final class PagesAPI {

    private final JavaPlugin plugin;
    private final PageManager pageManager;

    public PagesAPI(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pageManager = new PageManager();

        registerListeners();
    }

    public Page createPage(int size, String name) {
        return new Page(pageManager, size, name);
    }

    private void registerListeners() {
        plugin.getServer().getPluginManager().registerEvents(new InventoryClick(pageManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LeaveListener(pageManager), plugin);
    }

    public static PagesAPI init(JavaPlugin plugin) {
        return new PagesAPI(plugin);
    }

    public PageManager getPageManager() {
        return pageManager;
    }
}