package me.tomsoz.punishmentgui.punishmentgui;

import me.tomsoz.punishmentgui.punishmentgui.Commands.PunishGUICommand;
import me.tomsoz.punishmentgui.punishmentgui.Commands.PunishmentGUICommand;
import me.tomsoz.punishmentgui.punishmentgui.Configs.ConfigManager;
import me.tomsoz.punishmentgui.punishmentgui.Events.Chat;
import me.tomsoz.punishmentgui.punishmentgui.Events.InventoryClick;
import me.tomsoz.punishmentgui.punishmentgui.Events.PlayerInteract;
import me.tomsoz.punishmentgui.punishmentgui.Misc.UpdateChecker;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PunishmentGUI extends JavaPlugin {
    int spigotId = 94452;

    double configVer = 1.1D;

    ConfigManager manager = new ConfigManager(this);

    PluginDescriptionFile desc = getDescription();

    InventoryClick click = new InventoryClick(this);

    public void onEnable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        registerCommands();
        registerEvents();
        setupConfiguration();
        checkUpdate();
        updateConfig();
        sender.sendMessage(Utils.chat("&c" + this.desc.getName() + " &cv&4" + this.desc.getVersion() + " &cby &4" + (String)this.desc.getAuthors().get(0) + " &chas successfully enabled."));
    }

    public void onDisable() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Utils.chat("&c" + this.desc.getName() + " &cv&4" + this.desc.getVersion() + " &cby &4" + (String)this.desc.getAuthors().get(0) + " &chas successfully disabled."));
    }

    public void checkUpdate() {
        (new UpdateChecker(this, this.spigotId)).getLatestVersion(version -> {
            if (getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().log(Level.INFO, Utils.chat("&aYou're using the latest version"));
            } else {
                getLogger().log(Level.INFO, Utils.chat("&cYou're using an outdated version! Using " + getDescription().getVersion() + " latest " + version + "\n&cDownload at: https://www.spigotmc.org/resources/punishmentgui." + this.spigotId));
            }
        });
    }

    public void updateConfig() {
        if (this.manager.getConfig().getDouble("config-version") > this.configVer)
            this.manager.updateConfig();
    }

    public void registerCommands() {
        getCommand("punishmentgui").setExecutor((CommandExecutor)new PunishmentGUICommand(this));
        getCommand("punish").setExecutor((CommandExecutor)new PunishGUICommand(this, this.click));
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)this.click, (Plugin)this);
        pm.registerEvents((Listener)new Chat(this, this.click), (Plugin)this);
        pm.registerEvents((Listener)new PlayerInteract(this, this.click), (Plugin)this);
    }

    public void setupConfiguration() {
        this.manager.saveDefaultConfig();
        this.manager.saveDefaultSelPunishment();
        this.manager.saveDefaultSelReason();
        this.manager.saveDefaultSelTime();
        this.manager.saveDefaultSelSilent();
        this.manager.saveDefaultGUIConfirm();
    }

    public ConfigManager getConfigManager() {
        return this.manager;
    }
}
