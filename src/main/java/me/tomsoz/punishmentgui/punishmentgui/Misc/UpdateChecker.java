package me.tomsoz.punishmentgui.punishmentgui.Misc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private JavaPlugin plugin;
    private int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            URL urlVersion;
            URLConnection urlConnection;
            try {
                urlVersion = new URL("https://api.spigotmc.org/legacy/update.php?resource="+resourceId+"/");
                urlConnection = urlVersion.openConnection();
                BufferedReader versionReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String siteVersion = versionReader.readLine();
                consumer.accept(siteVersion);
                versionReader.close();
            }catch(Exception e) {
                plugin.getLogger().warning("An error occurred while checking for version updates, check your connection!");
            }
        });
    }
}
