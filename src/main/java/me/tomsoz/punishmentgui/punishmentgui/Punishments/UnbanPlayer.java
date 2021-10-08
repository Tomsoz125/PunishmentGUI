package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UnbanPlayer {
    Player executor;

    OfflinePlayer target;

    String reason;

    boolean silent;

    PunishmentGUI plugin;

    public UnbanPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, PunishmentGUI plugin) {
        this.executor = executor;
        this.target = target;
        this.reason = reason;
        this.silent = silent;
        this.plugin = plugin;
        unbanPlayer();
    }

    private void unbanPlayer() {
        List<String> commands;
        if (this.silent) {
            commands = this.plugin.getConfigManager().getConfig().getStringList("commands.silent.unban");
        } else {
            commands = this.plugin.getConfigManager().getConfig().getStringList("commands.public.unban");
        }
        for (String command : commands) {
            if (!command.equals("none")) {
                command = command.replace("%target%", this.target.getName()).replace("%executor%", this.executor.getName()).replace("%reason%", this.reason);
                Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), command);
            }
        }
        this.executor.sendMessage(Utils.chat("&aYou've sucessfully unbanned " + this.target.getName()));
    }
}
