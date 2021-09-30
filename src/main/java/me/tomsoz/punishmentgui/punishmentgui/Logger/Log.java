package me.tomsoz.punishmentgui.punishmentgui.Logger;

import me.tomsoz.punishmentgui.punishmentgui.Database.Database;
import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    Types type;
    Types typeRem;
    Player executor;
    OfflinePlayer target;
    String reason;
    String duration;
    boolean silent;
    public Log(Types typeRem, Types type, Player executor, OfflinePlayer target, String reason, String duration, boolean silent) {
        this.type=type;
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.duration=duration;
        this.silent=silent;
        this.typeRem=typeRem;
        log();
    }
    public void log() {
        Date now = new Date();
        int silentInt = 0;
        if (silent) {
            silentInt=1;
        } else {
            silentInt=0;
        }
        if (type.equals(Types.BAN)||
                type.equals(Types.WARN)||
                type.equals(Types.MUTE)||
                type.equals(Types.KICK)||
                type.equals(Types.TEMPMUTE)||
                type.equals(Types.TEMPWARN)) {
            Database.logPunishment(
                    "active",
                    now.getTime(),
                    target.getUniqueId().toString(),
                    executor.getUniqueId().toString(),
                    null,
                    type.toString(),
                    duration,
                    reason,
                    0,
                    silentInt
            );
        }
        if (type.equals(Types.UNWARN)||
                type.equals(Types.UNBAN)||
                type.equals(Types.UNMUTE)) {
            Database.logRevoke(
                    target.getUniqueId().toString(),
                    executor.getUniqueId().toString(),
                    now.getTime(),
                    typeRem.toString()
            );
        }
    }
}
