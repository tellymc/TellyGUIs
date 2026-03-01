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

    public Page(PageManager pageManager, int rows, String name) {
        this.pageManager = pageManager;
        inventory = Bukkit.createInventory(null, rows * 9, name);
    }

    public PageItem setSlot(int slot, ItemStack itemStack) {

        PageItem pageItem = new PageItem(itemStack);

        inventory.setItem(slot, pageItem.getItemStack());
        items.put(slot, pageItem);

        return pageItem;
    }

    public PageItem setSlot(int slot, ItemStack itemStack, String name) {

        PageItem pageItem = new PageItem(itemStack, name);

        inventory.setItem(slot, pageItem.getItemStack());
        items.put(slot, pageItem);

        return pageItem;
    }

    public PageItem setSlot(int slot, ItemStack itemStack, String name, String... lore) {

        PageItem pageItem = new PageItem(itemStack, name);
        pageItem.setLore(lore);

        inventory.setItem(slot, pageItem.getItemStack());
        items.put(slot, pageItem);

        return pageItem;
    }

    public void column(int column, ItemStack itemStack) {

        if (column < 0 || column > 9) return;
        column--;

        int rows = inventory.getSize() / 9;

        for (int i = 0; i < rows; i++) {

            setSlot((i * 9) + column, itemStack);
        }
    }

    public void column(int column, ItemStack itemStack, String name) {

        if (column < 0 || column > 9) return;
        column--;

        int rows = inventory.getSize() / 9;

        for (int i = 0; i < rows; i++) {

            setSlot((i * 9) + column, itemStack, name);
        }
    }

    public void column(int column, ItemStack itemStack, String name, String... lore) {

        if (column < 0 || column > 9) return;
        column--;

        int rows = inventory.getSize() / 9;

        for (int i = 0; i < rows; i++) {

            setSlot((i * 9) + column, itemStack, name, lore);
        }
    }

    public void row(int row, ItemStack itemStack) {

        int rows = inventory.getSize() / 9;
        row--;

        if (row < 0 || row >= rows) return;

        for (int i = 0; i < 9; i++) {

            setSlot((row * 9) + i, itemStack);
        }
    }

    public void row(int row, ItemStack itemStack, String name) {

        int rows = inventory.getSize() / 9;
        row--;

        if (row < 0 || row >= rows) return;

        for (int i = 0; i < 9; i++) {

            setSlot((row * 9) + i, itemStack, name);
        }
    }

    public void row(int row, ItemStack itemStack, String name, String... lore) {

        int rows = inventory.getSize() / 9;
        row--;

        if (row < 0 || row >= rows) return;

        for (int i = 0; i < 9; i++) {

            setSlot((row * 9) + i, itemStack, name, lore);
        }
    }

    public void outline(ItemStack itemStack) {

        int rows = inventory.getSize() / 9;

        row(1, itemStack);
        if (rows > 0) row(rows, itemStack);

        column(1, itemStack);
        column(9, itemStack);
    }

    public void outline(ItemStack itemStack, String name) {

        int rows = inventory.getSize() / 9;

        row(1, itemStack, name);
        if (rows > 0) row(rows, itemStack, name);

        column(1, itemStack, name);
        column(9, itemStack, name);
    }

    public void outline(ItemStack itemStack, String name, String... lore) {

        int rows = inventory.getSize() / 9;

        row(1, itemStack, name, lore);
        if (rows > 0) row(rows, itemStack, name, lore);

        column(1, itemStack, name, lore);
        column(9, itemStack, name, lore);
    }

    public void fill(ItemStack itemStack) {

        int items = inventory.getSize();

        for (int i = 0; i < items; i++) {
            setSlot(i, itemStack);
        }
    }

    public void fill(ItemStack itemStack, String name) {

        int items = inventory.getSize();

        for (int i = 0; i < items; i++) {
            setSlot(i, itemStack, name);
        }
    }

    public void fill(ItemStack itemStack, String name, String... lore) {

        int items = inventory.getSize();

        for (int i = 0; i < items; i++) {
            setSlot(i, itemStack, name, lore);
        }
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
        player.openInventory(inventory);
        pageManager.putPlayer(player, this);
    }

    public PageItem getItem(int slot) {
        return items.get(slot);
    }

    public HashMap<Integer, PageItem> getItems() {
        return this.items;
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