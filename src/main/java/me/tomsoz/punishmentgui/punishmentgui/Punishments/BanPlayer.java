package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BanPlayer {
    Player executor;

    OfflinePlayer target;

    String reason;

    boolean silent;

    String time;

    PunishmentGUI plugin;

    public BanPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, String time, PunishmentGUI plugin) {
        this.executor = executor;
        this.target = target;
        this.reason = reason;
        this.silent = silent;
        this.time = time;
        this.plugin = plugin;
        banPlayer();
    }

    private void banPlayer() {
        if (this.time == null || this.time.equals("PERM")) {
            List<String> commands;
            if (this.silent) {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.silent.ban");
            } else {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.public.ban");
            }
            for (String command : commands) {
                if (!command.equals("none")) {
                    command = command.replace("%target%", this.target.getName()).replace("%executor%", this.executor.getName()).replace("%reason%", this.reason).replace("%time%", this.time);
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
                }
            }
        } else {
            List<String> commands;
            if (this.silent) {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.silent.tempban");
            } else {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.public.tempban");
            }
            for (String command : commands) {
                if (!command.equals("none")) {
                    command = command.replace("%target%", this.target.getName()).replace("%executor%", this.executor.getName()).replace("%reason%", this.reason).replace("%time%", this.time);
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
                }
            }
            this.executor.sendMessage(Utils.chat("&aYou've sucessfully banned " + this.target.getName()));
        }
    }
}
