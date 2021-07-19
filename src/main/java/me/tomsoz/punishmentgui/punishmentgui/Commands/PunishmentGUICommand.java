package me.tomsoz.punishmentgui.punishmentgui.Commands;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishmentGUICommand implements CommandExecutor {
    PunishmentGUI plugin;
    public PunishmentGUICommand(PunishmentGUI plugin) {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendHelpMessage(sender);
            return true;
        }
        if (args[0].equals("help")) {
            sendHelpMessage(sender);
            return true;
        }
        if (args[0].equals("reload")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (!p.hasPermission("punishmentgui.reload")) {
                    p.sendMessage(Utils.chat("&fUnknown command. Type \"/help\" for help."));
                    return true;
                }
            }
            plugin.getConfigManager().reloadConfig();
            plugin.getConfigManager().reloadSelPunishment();
            plugin.getConfigManager().reloadSelReason();
            plugin.getConfigManager().reloadSelTime();
            plugin.getConfigManager().reloadSelSilent();
            plugin.getConfigManager().reloadGUIConfirm();
            sender.sendMessage(Utils.chat("&aYou've reloaded the configuration files."));
            return true;
        }
        return false;
    }

    public void sendHelpMessage(CommandSender p) {
        p.sendMessage(Utils.chat("&8&m-----&8[ &4PunishmentGUI &8]&8&m-----\n" +
                "&7&o - /punishmentgui help\n" +
                "&7&o - /punishmentgui deletehistory <id>\n" +
                "&7&o - /punishmentgui reload"));
    }
}
