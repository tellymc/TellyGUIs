package me.tellymc.pagesAPI.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class ChatInputManager {

    private final HashMap<UUID, Consumer<AsyncPlayerChatEvent>> chatInputs = new HashMap<>();

    public boolean contains(Player player) {
        return chatInputs.containsKey(player.getUniqueId());
    }

    public boolean contains(UUID uuid) {
        return chatInputs.containsKey(uuid);
    }

    public void handleChatInput(Player player, AsyncPlayerChatEvent event) {
        chatInputs.get(player.getUniqueId()).accept(event);
    }

    public void trackPlayerInput(Player player, Consumer<AsyncPlayerChatEvent> chatEventConsumer) {
        chatInputs.put(player.getUniqueId(), chatEventConsumer);
    }
}