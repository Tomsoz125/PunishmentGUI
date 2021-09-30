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

public class UnwarnPlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    PunishmentGUI plugin;
    public UnwarnPlayer(Player executor, OfflinePlayer target, String reason, boolean silent, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.plugin=plugin;
        unwarnPlayer();
    }
    private void unwarnPlayer() {
        if (!Database.isPlayerWarned(target)) {
            executor.sendMessage(Utils.chat("&c"+target.getName()+" &cis not warned!"));
            return;
        }
        List<String> commands;
        if (silent) {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.unwarn");
        } else {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.public.unwarn");
        }
        for (String command : commands) {
            if (!command.equals("none")) {
                command = command.replace("%target%", target.getName())
                        .replace("%executor%", executor.getName())
                        .replace("%reason%", reason);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
        new Log(Types.WARN, Types.UNWARN, executor, target, reason, null, silent);
        executor.sendMessage(Utils.chat("&aYou've sucessfully unwarned "+target.getName()));
    }
}
