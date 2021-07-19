package me.tomsoz.punishmentgui.punishmentgui.Configs;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class ConfigManager {
    public PunishmentGUI plugin;
    public ConfigManager(PunishmentGUI plugin) {
        this.plugin=plugin;
    }

    File configFile;
    FileConfiguration configCfg;

    File selPunishmentFile;
    FileConfiguration selPunishmentCfg;

    File selReasonFile;
    FileConfiguration selReasonCfg;

    File selTimeFile;
    FileConfiguration selTimeCfg;

    File selSilentFile;
    FileConfiguration selSilentCfg;

    File GUIConfirmFile;
    FileConfiguration GUIConfirmcfg;

    public FileConfiguration getConfig() {
        return configCfg;
    }
    public FileConfiguration getSelPunishment() {
        return selPunishmentCfg;
    }
    public FileConfiguration getSelReason() {
        return selReasonCfg;
    }
    public FileConfiguration getSelTime() {
        return selTimeCfg;
    }
    public FileConfiguration getSelSilent() {
        return selSilentCfg;
    }
    public FileConfiguration getGUIConfirm() { return GUIConfirmcfg; }


    public void saveConfig() {
        try {
            configCfg.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save config.yml!\nIf this persists contact the plugin developer."));
        }
    }
    public void saveSelPunishment() {
        try {
            selPunishmentCfg.save(selPunishmentFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save selectpunishment.yml!\nIf this persists contact the plugin developer."));
        }
    }
    public void saveSelReason() {
        try {
            selReasonCfg.save(selReasonFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save selectreason.yml!\nIf this persists contact the plugin developer."));
        }
    }
    public void saveSelTime() {
        try {
            selTimeCfg.save(selTimeFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save selecttime.yml!\nIf this persists contact the plugin developer."));
        }
    }
    public void saveSelSilent() {
        try {
            selTimeCfg.save(selSilentFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save selectsilent.yml!\nIf this persists contact the plugin developer."));
        }
    }
    public void saveGUIConfirm() {
        try {
            GUIConfirmcfg.save(GUIConfirmFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cCouldn't save confirm.yml"));
        }
    }

    public void updateConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", true);
        }
        configCfg = YamlConfiguration.loadConfiguration(configFile);
    }
    public void updateSelPunishment() {
        if (selPunishmentFile == null) {
            selPunishmentFile = new File(plugin.getDataFolder(), "menus/selectpunishment.yml");
        }
        if (!selPunishmentFile.exists()) {
            plugin.saveResource("menus/selectpunishment.yml", true);
        }
        selPunishmentCfg = YamlConfiguration.loadConfiguration(selPunishmentFile);
    }
    public void updateSelReason() {
        if (selReasonFile == null) {
            selReasonFile = new File(plugin.getDataFolder(), "menus/selectreason.yml");
        }
        if (!selReasonFile.exists()) {
            plugin.saveResource("menus/selectreason.yml", true);
        }
        selReasonCfg = YamlConfiguration.loadConfiguration(selReasonFile);
    }
    public void updateSelTime() {
        if (selTimeFile == null) {
            selTimeFile = new File(plugin.getDataFolder(), "menus/selecttime.yml");
        }
        if (!selTimeFile.exists()) {
            plugin.saveResource("menus/selecttime.yml", true);
        }
        selTimeCfg = YamlConfiguration.loadConfiguration(selTimeFile);
    }
    public void updateSelSilent() {
        if (selSilentFile == null) {
            selSilentFile = new File(plugin.getDataFolder(), "menus/selectsilent.yml");
        }
        if (!selSilentFile.exists()) {
            plugin.saveResource("menus/selectsilent.yml", true);
        }
        selSilentCfg = YamlConfiguration.loadConfiguration(selSilentFile);
    }
    public void updateGUIConfirm() {
        if (GUIConfirmFile == null) {
            GUIConfirmFile = new File(plugin.getDataFolder(), "menus/confirm.yml");
        }
        if (!GUIConfirmFile.exists()) {
            plugin.saveResource("menus/confirm.yml", true);
        }
        GUIConfirmcfg = YamlConfiguration.loadConfiguration(GUIConfirmFile);
    }

    public void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        configCfg = YamlConfiguration.loadConfiguration(configFile);
    }
    public void saveDefaultSelPunishment() {
        if (selPunishmentFile == null) {
            selPunishmentFile = new File(plugin.getDataFolder(), "menus/selectpunishment.yml");
        }
        if (!selPunishmentFile.exists()) {
            plugin.saveResource("menus/selectpunishment.yml", false);
        }
        selPunishmentCfg = YamlConfiguration.loadConfiguration(selPunishmentFile);
    }
    public void saveDefaultSelReason() {
        if (selReasonFile == null) {
            selReasonFile = new File(plugin.getDataFolder(), "menus/selectreason.yml");
        }
        if (!selReasonFile.exists()) {
            plugin.saveResource("menus/selectreason.yml", false);
        }
        selReasonCfg = YamlConfiguration.loadConfiguration(selReasonFile);
    }
    public void saveDefaultSelTime() {
        if (selTimeFile == null) {
            selTimeFile = new File(plugin.getDataFolder(), "menus/selecttime.yml");
        }
        if (!selTimeFile.exists()) {
            plugin.saveResource("menus/selecttime.yml", false);
        }
        selTimeCfg = YamlConfiguration.loadConfiguration(selTimeFile);
    }
    public void saveDefaultSelSilent() {
        if (selSilentFile == null) {
            selSilentFile = new File(plugin.getDataFolder(), "menus/selectsilent.yml");
        }
        if (!selSilentFile.exists()) {
            plugin.saveResource("menus/selectsilent.yml", false);
        }
        selSilentCfg = YamlConfiguration.loadConfiguration(selSilentFile);
    }
    public void saveDefaultGUIConfirm() {
        if (GUIConfirmFile == null) {
            GUIConfirmFile = new File(plugin.getDataFolder(), "menus/confirm.yml");
        }
        if (!GUIConfirmFile.exists()) {
            plugin.saveResource("menus/confirm.yml", false);
        }
        GUIConfirmcfg = YamlConfiguration.loadConfiguration(GUIConfirmFile);
    }



    public void reloadConfig() {
        configCfg = YamlConfiguration.loadConfiguration(configFile);
    }
    public void reloadSelPunishment() {
        selPunishmentCfg = YamlConfiguration.loadConfiguration(selPunishmentFile);
    }
    public void reloadSelReason() {
        selReasonCfg = YamlConfiguration.loadConfiguration(selReasonFile);
    }
    public void reloadSelTime() {
        selTimeCfg = YamlConfiguration.loadConfiguration(selTimeFile);
    }
    public void reloadSelSilent() {
        selSilentCfg = YamlConfiguration.loadConfiguration(selSilentFile);
    }
    public void reloadGUIConfirm() {
        GUIConfirmcfg = YamlConfiguration.loadConfiguration(GUIConfirmFile);
    }
}
