package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MutePlayer {
    Player executor;

    OfflinePlayer target;

    String reason;

    boolean silent;

    String time;

    PunishmentGUI plugin;

    public MutePlayer(Player executor, OfflinePlayer target, String reason, boolean silent, String time, PunishmentGUI plugin) {
        this.executor = executor;
        this.target = target;
        this.reason = reason;
        this.silent = silent;
        this.time = time;
        this.plugin = plugin;
        mutePlayer();
    }

    private void mutePlayer() {
        if (this.time == null) {
            List<String> commands;
            if (this.silent) {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.silent.mute");
            } else {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.public.mute");
            }
            for (String command : commands) {
                if (command.equals("none"))
                    return;
                command.replaceAll("%target%", this.target.getName());
                command.replaceAll("%executor%", this.executor.getName());
                command.replaceAll("%reason%", this.reason);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
        } else {
            List<String> commands;
            if (this.silent) {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.silent.tempmute");
            } else {
                commands = this.plugin.getConfigManager().getConfig().getStringList("commands.public.tempmute");
            }
            for (String command : commands) {
                if (!command.equals("none")) {
                    command = command.replace("%target%", this.target.getName()).replace("%executor%", this.executor.getName()).replace("%reason%", this.reason).replace("%time%", this.time);
                    Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), command);
                }
            }
            this.executor.sendMessage(Utils.chat("&aYou've sucessfully muted " + this.target.getName()));
        }
    }
}
