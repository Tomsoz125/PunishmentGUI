package me.tomsoz.punishmentgui.punishmentgui.Events;

import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectSilent;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectTime;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Chat implements Listener {
    PunishmentGUI plugin;
    InventoryClick click;
    public Chat(PunishmentGUI plugin, InventoryClick click) {
        this.plugin=plugin;
        this.click=click;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (click.getReasonChatInputs().contains(p)) {
            e.setCancelled(true);
            click.getReason.put(p, e.getMessage());
            click.getReasonChatInputs().remove(p);
            p.sendMessage(Utils.chat("&aYou've set the reason to: "+e.getMessage()));
            openSyncInventory(p, new SelectTime(click.getTarget.get(p), p, plugin).getInventory());
        }
        if (click.getTimeChatInputs().contains(p)) {
            e.setCancelled(true);
            click.getTime.put(p, e.getMessage());
            click.getTimeChatInputs().remove(p);
            p.sendMessage(Utils.chat("&aYou've set the time to: "+e.getMessage()));
            openSyncInventory(p, new SelectSilent(click.getTarget.get(p), p, plugin).getInventory());
        }
    }
    public void openSyncInventory(Player p, Inventory inv) {
        new BukkitRunnable() {
            @Override
            public void run() {
                p.openInventory(inv);
            }
        }.runTask(plugin);
    }
}
