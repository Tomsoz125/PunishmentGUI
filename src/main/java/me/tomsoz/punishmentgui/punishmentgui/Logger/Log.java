package me.tomsoz.punishmentgui.punishmentgui.Logger;

import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import org.bukkit.entity.Player;

public class Log {
    Types type;
    Player executor;
    Player target;
    String reason;
    String time;
    public Log(Types type, Player executor, Player target, String reason, String time) {
        this.type=type;
        this.executor=executor;
        this.target=target;
        this.reason=reason;
        this.time=time;
        log();
    }
    public void log() {
        if (type.equals(Types.BAN)) {

        }
    }
    public void setup() {

    }
}
