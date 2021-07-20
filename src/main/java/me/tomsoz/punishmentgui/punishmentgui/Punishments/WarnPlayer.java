package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Enums.LogTypes;
import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import me.tomsoz.punishmentgui.punishmentgui.Logger.Log;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class WarnPlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    String time;
    PunishmentGUI plugin;
    public WarnPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, String time, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.time=time;
        this.plugin=plugin;
        warnPlayer();
    }
    private void warnPlayer() {
        if (time==null) {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.warn");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.warn");
            }
            for (String command : commands) {
                if (command.equals("none")) {
                    return;
                }
                command.replaceAll("%target%", target.getName());
                command.replaceAll("%executor%", executor.getName());
                command.replaceAll("%reason%", reason);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
            new Log(LogTypes.ADD, Types.WARN, executor, target, reason, time);
            executor.sendMessage(Utils.chat("&aYou've sucessfully warned "+target.getName()));
        } else {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.tempwarn");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.tempwarn");
            }
            for (String command : commands) {
                if (!command.equals("none")) {
                    command = command.replace("%target%", target.getName())
                            .replace("%executor%", executor.getName())
                            .replace("%reason%", reason)
                            .replace("%time%", time);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
            new Log(LogTypes.ADD, Types.TEMPWARN, executor, target, reason, time);
            executor.sendMessage(Utils.chat("&aYou've sucessfully warned "+target.getName()));
        }
    }
}
