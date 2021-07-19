package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class BanPlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    String time;
    PunishmentGUI plugin;
    public BanPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, String time, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.time=time;
        this.plugin=plugin;
        banPlayer();
    }
    private void banPlayer() {
        if (time==null||time.equals("PERM")) {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.ban");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.ban");
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
            //new Log(Types.BAN, executor, target, reason, null);
        } else {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.tempban");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.tempban");
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
            executor.sendMessage(Utils.chat("&aYou've sucessfully banned "+target.getName()));
            //new Log(Types.TEMPBAN, executor, target, reason, time);
        }
    }
}
