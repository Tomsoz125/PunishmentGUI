package me.tomsoz.punishmentgui.punishmentgui.Events;

import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectReason;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectSilent;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    PunishmentGUI plugin;
    InventoryClick click;
    public PlayerInteract(PunishmentGUI plugin, InventoryClick click) {
        this.plugin=plugin;
        this.click=click;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)||e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (click.getReasonChatInputs().contains(p)) {
                e.setCancelled(true);
                click.getReasonChatInputs().remove(p);
                p.sendMessage(Utils.chat("&cYou've cancelled the custom reason."));
                p.openInventory(new SelectReason(click.getTarget.get(p), p, plugin).getInventory());
            }
            if (click.getTimeChatInputs().contains(p)) {
                e.setCancelled(true);
                click.getTimeChatInputs().remove(p);
                p.sendMessage(Utils.chat("&cYou've cancelled the custom time."));
                p.openInventory(new SelectSilent(click.getTarget.get(p), p, plugin).getInventory());
            }
        }
    }
}
