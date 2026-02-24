package me.tellymc.pagesAPI.objects;

import me.tellymc.pagesAPI.managers.PageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Page {

    private final PageManager pageManager;

    private final HashMap<Integer, PageItem> items = new HashMap<>();
    private final Inventory inventory;
    private Page lastPage;
    private Page nextPage;

    public Page(PageManager pageManager, int size, String name) {
        this.pageManager = pageManager;
        inventory = Bukkit.createInventory(null, size, name);
    }

    public PageItem setSlot(int slot, ItemStack itemStack, String name) {

        PageItem pageItem = new PageItem(itemStack, name);

        inventory.setItem(slot, pageItem.getItemStack());
        items.put(slot, pageItem);

        return pageItem;
    }

    public void handleClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();

        if (slot < 0 || slot >= inventory.getSize()) return;
        if (!items.containsKey(slot)) return;

        PageItem pageItem = items.get(slot);

        pageItem.handleClick(event);
    }

    public void setLastPage(Page page) {
        this.lastPage = page;
    }

    public void setNextPage(Page page) {
        this.nextPage = page;
    }

    public void open(Player player) {
        if (!pageManager.containsPlayer(player)) return;
        player.openInventory(inventory);
        pageManager.putPlayer(player, this);
    }

    public void openLastPage(Player player) {
        if (lastPage == null) return;
        player.openInventory(lastPage.getInventory());
        pageManager.putPlayer(player, lastPage);
    }

    public void openNextPage(Player player) {
        if (nextPage == null) return;
        player.openInventory(nextPage.getInventory());
        pageManager.putPlayer(player, nextPage);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    protected Page getLastPage() {
        return this.lastPage;
    }

    protected Page getNextPage() {
        return this.nextPage;
    }
}