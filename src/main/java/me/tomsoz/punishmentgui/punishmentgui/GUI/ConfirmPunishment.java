package me.tomsoz.punishmentgui.punishmentgui.GUI;

import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ConfirmPunishment {
    OfflinePlayer target;
    Player executor;
    PunishmentGUI plugin;
    String reason;
    String time;
    Types type;
    Boolean silent;
    public ConfirmPunishment(OfflinePlayer target, Player executor, String reason, String time, Boolean silent, Types type, PunishmentGUI plugin) {
        this.target=target;
        this.executor=executor;
        this.plugin=plugin;
        this.reason=reason;
        this.time=time;
        this.silent=silent;
        this.type=type;
    }
    public Inventory getInventory() {
        FileConfiguration cfg = plugin.getConfigManager().getGUIConfirm();
        String publicicy;
        if (silent) {
            publicicy = "Silent";
        } else {
            publicicy = "Public";
        }
        int guiSlots = cfg.getInt("gui.rows")*9;
        String guiName = Utils.chat(cfg.getString("gui.name")
                .replaceAll("%punishment%", type.toString())
                .replaceAll("%target%", target.getName()))
                .replaceAll("%executor%", executor.getName())
                .replaceAll("%time%", time)
                .replaceAll("%publicity%", publicicy)
                .replaceAll("%reason%", reason);
        Inventory inv = Bukkit.createInventory(null, guiSlots, guiName);

        for (String key : cfg.getConfigurationSection("items").getKeys(false)) {
            Material mat = Material.getMaterial(cfg.getString("items."+key+".material"));
            if (!(mat == null)) {
                int slot = cfg.getInt("items."+key+".slot")-1;
                String name = Utils.chat(cfg.getString("items."+key+".name")
                        .replaceAll("%punishment%", type.toString())
                        .replaceAll("%target%", target.getName()))
                        .replaceAll("%executor%", executor.getName())
                        .replaceAll("%time%", time)
                        .replaceAll("%publicity%", publicicy
                        );
                List<String> cfglore = cfg.getStringList("items."+key+".lore");
                List<String> lore = new ArrayList<>();
                for (String s : cfglore)
                    lore.add(Utils.chat(s
                            .replaceAll("%punishment%", type.toString())
                            .replaceAll("%target%", target.getName()))
                            .replaceAll("%executor%", executor.getName())
                            .replaceAll("%time%", time)
                            .replaceAll("%publicity%", publicicy)
                            .replaceAll("%reason%", reason));
                List<String> permcfglore = cfg.getStringList("items."+key+".permission_lore");
                List<String> permlore = new ArrayList<>();
                for (String s : permcfglore)
                    permlore.add(Utils.chat(s
                            .replaceAll("%punishment%", type.toString())
                            .replaceAll("%target%", target.getName()))
                            .replaceAll("%executor%", executor.getName())
                            .replaceAll("%time%", time)
                            .replaceAll("%publicity%", publicicy)
                            .replaceAll("%reason%", reason));
                String perm = cfg.getString("items."+key+".permission");

                ItemStack item = new ItemStack(mat);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                if (executor.hasPermission(perm)) {
                    meta.setLore(lore);
                } else {
                    meta.setLore(permlore);
                }
                item.setItemMeta(meta);
                inv.setItem(slot, item);
            } else {
                Bukkit.getLogger().log(Level.WARNING, Utils.chat("&c"+key+" material isn't valid for the SelectPunishment GUI."));
            }
        }



        Material blankMat = Material.getMaterial(cfg.getString("gui.blank.material"));
        if (blankMat == null ){
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cBlank material isn't valid for the SelectPunishment GUI."));
        } else {
            ItemStack blankItem = new ItemStack(blankMat);
            ItemMeta blankMeta = blankItem.getItemMeta();
            blankMeta.setDisplayName(Utils.chat(cfg.getString("gui.blank.name")));
            List<String> cfglore = cfg.getStringList("gui.blank.lore");
            List<String> lore = new ArrayList<>();
            for (String s : cfglore)
                lore.add(Utils.chat(s));
            blankMeta.setLore(lore);
            blankItem.setItemMeta(blankMeta);
            int i = 0;
            for (ItemStack item : inv.getContents()) {
                if (item == null || item.getType().equals(Material.AIR)) {
                    inv.setItem(i, blankItem);
                }
                i++;
            }
        }
        return inv;
    }
}
