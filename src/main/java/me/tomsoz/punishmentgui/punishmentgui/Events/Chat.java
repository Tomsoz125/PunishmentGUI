package me.tomsoz.punishmentgui.punishmentgui.Events;

import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectSilent;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectTime;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
    PunishmentGUI plugin;

    InventoryClick click;

    public Chat(PunishmentGUI plugin, InventoryClick click) {
        this.plugin = plugin;
        this.click = click;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (this.click.getReasonChatInputs().contains(p)) {
            e.setCancelled(true);
            this.click.getReason.put(p, e.getMessage());
            this.click.getReasonChatInputs().remove(p);
            p.sendMessage(Utils.chat("&aYou've set the reason to: " + e.getMessage()));
            p.openInventory((new SelectTime(this.click.getTarget.get(p), p, this.plugin)).getInventory());
        }
        if (this.click.getTimeChatInputs().contains(p)) {
            e.setCancelled(true);
            this.click.getTime.put(p, e.getMessage());
            this.click.getTimeChatInputs().remove(p);
            p.sendMessage(Utils.chat("&aYou've set the time to: " + e.getMessage()));
            p.openInventory((new SelectSilent(this.click.getTarget.get(p), p, this.plugin)).getInventory());
        }
    }
}
