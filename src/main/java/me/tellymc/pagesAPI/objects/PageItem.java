package me.tellymc.pagesAPI.objects;

import me.tellymc.pagesAPI.Utils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PageItem {

    private final ItemStack itemStack;
    private String name;
    private Consumer<InventoryClickEvent> clickAction;
    private List<String> lore = new ArrayList<>();

    public PageItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public PageItem(ItemStack itemStack, String name) {
        this.itemStack = itemStack;

        setName(name);
    }

    public PageItem(ItemStack itemStack, String name, String... lore) {
        this.itemStack = itemStack;

        setName(name);
        setLore(lore);
    }

    public void setName(String name) {

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(Utils.translate(name));
        itemStack.setItemMeta(itemMeta);

        this.name = name;
    }

    public void setLore(String... lore) {

        List<String> newLore = new ArrayList<>();

        for (String line : lore) {
            newLore.add(Utils.translate(line));
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);

        this.lore = newLore;
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

    protected List<String> getLore() {
        return this.lore;
    }
}