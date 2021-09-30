package me.tomsoz.punishmentgui.punishmentgui.Events;

import me.tomsoz.punishmentgui.punishmentgui.Enums.Types;
import me.tomsoz.punishmentgui.punishmentgui.GUI.ConfirmPunishment;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectReason;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectSilent;
import me.tomsoz.punishmentgui.punishmentgui.GUI.SelectTime;
import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import me.tomsoz.punishmentgui.punishmentgui.Punishments.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryClick implements Listener {
    PunishmentGUI plugin;
    public InventoryClick(PunishmentGUI plugin) {
        this.plugin=plugin;
    }

    public ArrayList<Player> isReasonChatInput = new ArrayList<>();
    public ArrayList<Player> isTimeChatInput = new ArrayList<>();

    public HashMap<Player, OfflinePlayer> getTarget = new HashMap<>();
    public HashMap<Player, Types> getType = new HashMap<>();
    public HashMap<Player, String> getReason = new HashMap<>();
    public HashMap<Player, String> getTime = new HashMap<>();
    public HashMap<Player, Boolean> getSilent = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle() == null)return;
        if (e.getInventory().getType().equals(InventoryType.PLAYER))return;
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            FileConfiguration punishmentCfg = plugin.getConfigManager().getSelPunishment();
            if (e.getView().getTitle().equals(Utils.chat(punishmentCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : punishmentCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items."+key+".";
                    if (punishmentCfg.getInt(path+"slot")-1 == e.getSlot()) {
                        if (punishmentCfg.getString(path+"type").equals("PLACEHOLDER"))return;
                        getType.put(p, Types.valueOf(punishmentCfg.getString(path+"type")));
                        if (Types.valueOf(punishmentCfg.getString(path+"type")).equals(Types.UNBAN)||
                                Types.valueOf(punishmentCfg.getString(path+"type")).equals(Types.UNMUTE)||
                                Types.valueOf(punishmentCfg.getString(path+"type")).equals(Types.UNWARN)) {
                            getTime.put(p, null);
                            p.openInventory(new SelectReason(getTarget.get(p), p, plugin).getInventory());
                            return;
                        }
                        Inventory inv = new SelectTime(getTarget.get(p), p, plugin).getInventory();
                        p.openInventory(inv);
                    }
                }
            }
            FileConfiguration reasonCfg = plugin.getConfigManager().getSelReason();
            if (e.getView().getTitle().equals(Utils.chat(reasonCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : reasonCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items."+key+".";
                    if (reasonCfg.getInt(path+"slot")-1 == e.getSlot()) {
                        if (reasonCfg.getString(path+"reason").equals("PLACEHOLDER"))return;
                        String reason = reasonCfg.getString(path+"reason");
                        if (reason.equals("CUSTOM")) {
                            isReasonChatInput.add(p);
                            p.sendMessage(Utils.chat("&aPlease type the reason in chat.\nLeft click to cancel."));
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                if (isReasonChatInput.contains(p)) {
                                    isReasonChatInput.remove(p);
                                    p.sendMessage(Utils.chat("&cYou ran out of time."));
                                    p.openInventory(new SelectReason(getTarget.get(p), p, plugin).getInventory());
                                }
                            }, 15*20);
                            return;
                        }
                        getReason.put(p, reason);
                        p.openInventory(new SelectSilent(getTarget.get(p), p, plugin).getInventory());
                    }
                }
            }
            FileConfiguration timeCfg = plugin.getConfigManager().getSelTime();
            if (e.getView().getTitle().equals(Utils.chat(timeCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : timeCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items."+key+".";
                    if (timeCfg.getInt(path+"slot")-1 == e.getSlot()) {
                        if (timeCfg.getString(path+"time").equals("PLACEHOLDER"))return;
                        String time = timeCfg.getString(path+"time");
                        if (time.equals("CUSTOM")) {
                            isTimeChatInput.add(p);
                            p.sendMessage(Utils.chat("&aPlease type the time in chat.\nLeft click to cancel."));
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                if (isTimeChatInput.contains(p)) {
                                    isTimeChatInput.remove(p);
                                    p.sendMessage(Utils.chat("&cYou ran out of time."));
                                    p.openInventory(new SelectTime(getTarget.get(p), p, plugin).getInventory());
                                }
                            }, 15*20);
                            return;
                        }
                        getTime.put(p, time);
                        p.openInventory(new SelectReason(getTarget.get(p), p, plugin).getInventory());
                    }
                }
            }
            FileConfiguration silentCfg = plugin.getConfigManager().getSelSilent();
            if (e.getView().getTitle().equals(Utils.chat(silentCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : silentCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items."+key+".";
                    if (silentCfg.getInt(path+"slot")-1 == e.getSlot()) {
                        if (silentCfg.getString(path+"publicity").equals("PLACEHOLDER"))return;
                        String publicity = silentCfg.getString(path+"publicity");
                        if (publicity.equals("SILENT")) {
                            getSilent.put(p, true);
                        }
                        if (publicity.equals("PUBLIC")) {
                            getSilent.put(p, false);
                        }
                        p.openInventory(new ConfirmPunishment(getTarget.get(p), p, getReason.get(p), getTime.get(p), getSilent.get(p), getType.get(p), plugin).getInventory());
                    }
                }
            }
            FileConfiguration confirmCfg = plugin.getConfigManager().getGUIConfirm();
            if (e.getView().getTitle().equals(Utils.chat(confirmCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : confirmCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items."+key+".";
                    if (confirmCfg.getInt(path+"slot")-1 == e.getSlot()) {
                        if (confirmCfg.getString(path+"type").equals("PLACEHOLDER"))return;
                        String type1 = confirmCfg.getString(path+"type");
                        if (type1.equals("CONFIRM")) {
                            Types type = getType.get(p);
                            p.closeInventory();
                            if (type.equals(Types.BAN)) {
                                new BanPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            if (type.equals(Types.MUTE)) {
                                new MutePlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            else if (type.equals(Types.KICK)) {
                                new KickPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), plugin);
                            }
                            else if (type.equals(Types.WARN)) {
                                new WarnPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            else if (type.equals(Types.TEMPWARN)) {
                                new WarnPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            if (type.equals(Types.TEMPBAN)) {
                                new BanPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            else if (type.equals(Types.TEMPMUTE)) {
                                new MutePlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), getTime.get(p), plugin);
                            }
                            else if (type.equals(Types.UNWARN)) {
                                new UnwarnPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), plugin);
                            }
                            else if (type.equals(Types.UNBAN)) {
                                new UnbanPlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), plugin);
                            }
                            else if (type.equals(Types.UNMUTE)) {
                                new UnmutePlayer(p, getTarget.get(p), getReason.get(p), getSilent.get(p), plugin);
                            }
                        }
                        if (type1.equals("CANCEL")) {
                            p.closeInventory();
                            p.sendMessage(Utils.chat("&cYou've cancelled the punishment."));
                            return;
                        }
                        if (type1.equals("PLACEHOLDER"))return;
                    }
                }
            }
        }
    }

    public void setMapFromCmd(Player p, OfflinePlayer target) {
        if (getTarget.containsKey(p)) {
            getTarget.replace(p, target);
        } else {
            getTarget.put(p, target);
        }

    }

    public ArrayList<Player> getReasonChatInputs() { return isReasonChatInput; }
    public ArrayList<Player> getTimeChatInputs() { return isTimeChatInput; }
}
