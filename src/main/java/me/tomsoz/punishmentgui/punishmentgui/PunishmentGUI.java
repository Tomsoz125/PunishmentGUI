package me.tomsoz.punishmentgui.punishmentgui;

import me.tomsoz.punishmentgui.punishmentgui.Commands.PunishGUICommand;
import me.tomsoz.punishmentgui.punishmentgui.Commands.PunishmentGUICommand;
import me.tomsoz.punishmentgui.punishmentgui.Configs.ConfigManager;
import me.tomsoz.punishmentgui.punishmentgui.Database.Database;
import me.tomsoz.punishmentgui.punishmentgui.Events.Chat;
import me.tomsoz.punishmentgui.punishmentgui.Events.InventoryClick;
import me.tomsoz.punishmentgui.punishmentgui.Events.PlayerInteract;
import me.tomsoz.punishmentgui.punishmentgui.Misc.UpdateChecker;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class PunishmentGUI extends JavaPlugin {
    int spigotId = 94452;
    double configVer = 1.1;
    private static String connectionURL;
    ConfigManager manager = new ConfigManager(this);
    PluginDescriptionFile desc = this.getDescription();
    InventoryClick click = new InventoryClick(this);
    @Override
    public void onEnable() {
        connectionURL = getDataFolder().getAbsolutePath()+"/data/PunishmentGUI";
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        registerCommands();
        registerEvents();
        setupConfiguration();
        checkUpdate();
        updateConfig();
        Database.initialiseDatabase();
        sender.sendMessage(Utils.chat("&c"+desc.getName()+" &cv&4"+desc.getVersion()+" &cby &4"+desc.getAuthors().get(0)+" &chas successfully enabled."));
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Utils.chat("&c"+desc.getName()+" &cv&4"+desc.getVersion()+" &cby &4"+desc.getAuthors().get(0)+" &chas successfully disabled."));
    }

    public void checkUpdate() {
        new UpdateChecker(this, spigotId).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                this.getLogger().log(Level.INFO, Utils.chat("&aYou're using the latest version"));
            } else {
                this.getLogger().log(Level.INFO, Utils.chat("&cYou're using an outdated version! Using "+this.getDescription().getVersion()+" latest "+version+"\n" +
                        "&cDownload at: https://www.spigotmc.org/resources/punishmentgui."+spigotId));
            }
        });
    }
    public void updateConfig() {
        if (!(manager.getConfig().getDouble("config-version") <= configVer)) {
            manager.updateConfig();
        }
    }

    public void registerCommands() {
        getCommand("punishmentgui").setExecutor(new PunishmentGUICommand(this));
        getCommand("punish").setExecutor(new PunishGUICommand(this, click));
    }
    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(click, this);
        pm.registerEvents(new Chat(this, click), this);
        pm.registerEvents(new PlayerInteract(this, click), this);
    }
    public void setupConfiguration() {
        manager.saveDefaultConfig();
        manager.saveDefaultSelPunishment();
        manager.saveDefaultSelReason();
        manager.saveDefaultSelTime();
        manager.saveDefaultSelSilent();
        manager.saveDefaultGUIConfirm();
    }
    public ConfigManager getConfigManager() {
        return manager;
    }

    public static String getConnectionURL(){
        return connectionURL;
    }
}
