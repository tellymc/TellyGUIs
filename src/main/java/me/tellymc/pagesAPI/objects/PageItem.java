package me.tellymc.pagesAPI.objects;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class PageItem {

    private final ItemStack itemStack;
    private String name;
    private Consumer<InventoryClickEvent> clickAction;

    public PageItem(ItemStack itemStack, String name) {
        this.itemStack = itemStack;

        setName(name);
    }

    public void setName(String name) {

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        this.name = name;
    }

    public void setClickAction(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
    }

    public void handleClick(InventoryClickEvent event) {
        clickAction.accept(event);
    }

    protected ItemStack getItemStack() {
        return this.itemStack;
    }

    protected String getName() {
        return this.name;
    }
}