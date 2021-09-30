package me.tomsoz.punishmentgui.punishmentgui.Punishments;

import me.tomsoz.punishmentgui.punishmentgui.Database.Database;
import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import me.tomsoz.punishmentgui.punishmentgui.Logger.Log;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class UnbanPlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    PunishmentGUI plugin;
    public UnbanPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.plugin=plugin;
        unbanPlayer();
    }
    private void unbanPlayer() {
        if (!Database.isPlayerBanned(target)) {
            executor.sendMessage(Utils.chat("&c"+target.getName()+" &cis not banned!"));
            return;
        }
        List<String> commands;
        if (silent) {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.unban");
        } else {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.public.unban");
        }
        for (String command : commands) {
            if (!command.equals("none")) {
                command = command.replace("%target%", target.getName())
                        .replace("%executor%", executor.getName())
                        .replace("%reason%", reason);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
        executor.sendMessage(Utils.chat("&aYou've sucessfully unbanned "+target.getName()));
        new Log(Types.BAN, Types.UNBAN, executor, target, reason, null, silent);
    }
}
