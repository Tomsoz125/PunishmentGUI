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

public class UnmutePlayer {
    Player executor;
    OfflinePlayer target;
    String reason;
    boolean silent;
    PunishmentGUI plugin;
    public UnmutePlayer(Player executor, OfflinePlayer target, String reason, boolean silent, PunishmentGUI plugin){
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.silent=silent;
        this.plugin=plugin;
        unmutePlayer();
    }
    private void unmutePlayer() {
        List<String> commands;
        if (silent) {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.silent.unmute");
        } else {
            commands = plugin.getConfigManager().getConfig().getStringList("commands.public.unmute");
        }
        for (String command : commands) {
            if (!command.equals("none")) {
                command = command.replace("%target%", target.getName())
                        .replace("%executor%", executor.getName())
                        .replace("%reason%", reason);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
        new Log(LogTypes.ADD, Types.UNMUTE, executor, target, reason, null);
        executor.sendMessage(Utils.chat("&aYou've sucessfully unmuted "+target.getName()));
    }
}
