package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class MutePlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    String time;
    PunishmentGUI plugin;
    public MutePlayer(Player executor, OfflinePlayer target, String reason, boolean silent, String time, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.time=time;
        this.plugin=plugin;
        mutePlayer();
    }
    private void mutePlayer() {
        if (time==null) {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.mute");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.mute");
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
            //new Log(Types.MUTE, executor, target, reason, null);
        } else {
            List<String> commands;
            if (silent) {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.tempmute");
            } else {
                commands = plugin.getConfigManager().getConfig().getStringList("commands.public.tempmute");
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
            executor.sendMessage(Utils.chat("&aYou've sucessfully muted "+target.getName()));
            //new Log(Types.TEMPMUTE, executor, target, reason, time);
        }
    }
}
