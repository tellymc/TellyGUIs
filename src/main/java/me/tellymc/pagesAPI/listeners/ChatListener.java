package me.tellymc.pagesAPI.listeners;

import me.tellymc.pagesAPI.PagesAPI;
import me.tellymc.pagesAPI.managers.ChatInputManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final PagesAPI plugin;

    public ChatListener(PagesAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        ChatInputManager chatInputManager = plugin.getChatInputManager();
        Player player = event.getPlayer();

        if (!chatInputManager.contains(player.getUniqueId())) return;

        chatInputManager.handleChatInput(player, event);
    }
}