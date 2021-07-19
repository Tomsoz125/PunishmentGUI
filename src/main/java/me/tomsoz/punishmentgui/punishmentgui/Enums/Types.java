package me.tomsoz.punishmentgui.punishmentgui.Enums;

public enum Types {
    BAN ("Ban"),
    MUTE ("Mute"),
    KICK ("Kick"),
    WARN ("Warn"),
    UNBAN ("Unban"),
    UNMUTE ("Unmute"),
    UNWARN ("Unwarn"),
    TEMPMUTE ("Tempmute"),
    TEMPBAN ("Tempban"),
    TEMPWARN ("Tempwarn");
    String label;
    Types(String label) {
        this.label=label;
    }
}
