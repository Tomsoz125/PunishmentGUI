package me.tomsoz.punishmentgui.punishmentgui.Commands;

import me.tomsoz.punishmentgui.punishmentgui.Events.InventoryClick;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectPunishment;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@SuppressWarnings("deprecation")
public class PunishGUICommand implements CommandExecutor {
    PunishmentGUI plugin;
    InventoryClick click;
    public PunishGUICommand(PunishmentGUI plugin, InventoryClick click) {
        this.plugin=plugin;
        this.click=click;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(args.length > 0)) {
            sender.sendMessage(Utils.chat("&cUsage: /punish <player>"));
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.chat("&cThis is a player command!"));
                return true;
            }
            Player p = (Player)sender;
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null) {
                p.sendMessage(Utils.chat("&cUsage: /punish <player>"));
                return true;
            }
            click.setMapFromCmd(p, target);
            Inventory inv = new SelectPunishment(target, p, plugin).getInventory();
            p.openInventory(inv);
        }
        return false;
    }
}
