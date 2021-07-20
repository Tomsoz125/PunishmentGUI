package me.tomsoz.punishmentgui.punishmentgui.Logger;

import me.tomsoz.punishmentgui.punishmentgui.Database.Database;
import me.tomsoz.punishmentgui.punishmentgui.Enums.LogTypes;
import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;

public class Log {
    Types type;
    Player executor;
    OfflinePlayer target;
    String reason;
    String time;
    boolean silent;
    LogTypes logtype;
    public Log(LogTypes logtype, Types type, Player executor, OfflinePlayer target, String reason, String time) {
        this.type=type;
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.time=time;
        this.silent=silent;
        this.logtype=logtype;
        log();
    }
    public void log() {
        int isSilent;
        if (silent) {
            isSilent = 1;
        } else {
            isSilent = 0;
        }
        Database.addPunishment(logtype.name(), new Date().getTime(), target.getUniqueId().toString(), executor.getUniqueId().toString(), null, type.name(), time, reason, -1, isSilent);
    }
}
