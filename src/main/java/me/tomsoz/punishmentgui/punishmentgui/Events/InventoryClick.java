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
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryClick implements Listener {
    PunishmentGUI plugin;

    public ArrayList<Player> isReasonChatInput;

    public ArrayList<Player> isTimeChatInput;

    public HashMap<Player, OfflinePlayer> getTarget;

    public HashMap<Player, Types> getType;

    public HashMap<Player, String> getReason;

    public HashMap<Player, String> getTime;

    public HashMap<Player, Boolean> getSilent;

    public InventoryClick(PunishmentGUI plugin) {
        this.isReasonChatInput = new ArrayList<>();
        this.isTimeChatInput = new ArrayList<>();
        this.getTarget = new HashMap<>();
        this.getType = new HashMap<>();
        this.getReason = new HashMap<>();
        this.getTime = new HashMap<>();
        this.getSilent = new HashMap<>();
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle() == null)
            return;
        if (e.getInventory().getType().equals(InventoryType.PLAYER))
            return;
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            FileConfiguration punishmentCfg = this.plugin.getConfigManager().getSelPunishment();
            if (e.getView().getTitle().equals(Utils.chat(punishmentCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : punishmentCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items." + key + ".";
                    if (punishmentCfg.getInt(path + "slot") - 1 == e.getSlot()) {
                        if (punishmentCfg.getString(path + "type").equals("PLACEHOLDER"))
                            return;
                        this.getType.put(p, Types.valueOf(punishmentCfg.getString(path + "type")));
                        Inventory inv = (new SelectTime(this.getTarget.get(p), p, this.plugin)).getInventory();
                        p.openInventory(inv);
                    }
                }
            }
            FileConfiguration reasonCfg = this.plugin.getConfigManager().getSelReason();
            if (e.getView().getTitle().equals(Utils.chat(reasonCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : reasonCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items." + key + ".";
                    if (reasonCfg.getInt(path + "slot") - 1 == e.getSlot()) {
                        if (reasonCfg.getString(path + "reason").equals("PLACEHOLDER"))
                            return;
                        String reason = reasonCfg.getString(path + "reason");
                        if (reason.equals("CUSTOM")) {
                            this.isReasonChatInput.add(p);
                            p.sendMessage(Utils.chat("&aPlease type the reason in chat.\nLeft click to cancel."));
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, () -> {
                                if (this.isReasonChatInput.contains(p)) {
                                    this.isReasonChatInput.remove(p);
                                    p.sendMessage(Utils.chat("&cYou ran out of time."));
                                    p.openInventory((new SelectReason(this.getTarget.get(p), p, this.plugin)).getInventory());
                                }
                            }, 300L);
                            return;
                        }
                        this.getReason.put(p, reason);
                        p.openInventory((new SelectSilent(this.getTarget.get(p), p, this.plugin)).getInventory());
                    }
                }
            }
            FileConfiguration timeCfg = this.plugin.getConfigManager().getSelTime();
            if (e.getView().getTitle().equals(Utils.chat(timeCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : timeCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items." + key + ".";
                    if (timeCfg.getInt(path + "slot") - 1 == e.getSlot()) {
                        if (timeCfg.getString(path + "time").equals("PLACEHOLDER"))
                            return;
                        String time = timeCfg.getString(path + "time");
                        if (time.equals("CUSTOM")) {
                            this.isTimeChatInput.add(p);
                            p.sendMessage(Utils.chat("&aPlease type the time in chat.\nLeft click to cancel."));
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, () -> {
                                if (this.isTimeChatInput.contains(p)) {
                                    this.isTimeChatInput.remove(p);
                                    p.sendMessage(Utils.chat("&cYou ran out of time."));
                                    p.openInventory((new SelectTime(this.getTarget.get(p), p, this.plugin)).getInventory());
                                }
                            }, 300L);
                            return;
                        }
                        this.getTime.put(p, time);
                        p.openInventory((new SelectReason(this.getTarget.get(p), p, this.plugin)).getInventory());
                    }
                }
            }
            FileConfiguration silentCfg = this.plugin.getConfigManager().getSelSilent();
            if (e.getView().getTitle().equals(Utils.chat(silentCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : silentCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items." + key + ".";
                    if (silentCfg.getInt(path + "slot") - 1 == e.getSlot()) {
                        if (silentCfg.getString(path + "publicity").equals("PLACEHOLDER"))
                            return;
                        String publicity = silentCfg.getString(path + "publicity");
                        if (publicity.equals("SILENT"))
                            this.getSilent.put(p, Boolean.valueOf(true));
                        if (publicity.equals("PUBLIC"))
                            this.getSilent.put(p, Boolean.valueOf(false));
                        p.openInventory((new ConfirmPunishment(this.getTarget.get(p), p, this.getReason.get(p), this.getTime.get(p), this.getSilent.get(p), this.getType.get(p), this.plugin)).getInventory());
                    }
                }
            }
            FileConfiguration confirmCfg = this.plugin.getConfigManager().getGUIConfirm();
            if (e.getView().getTitle().equals(Utils.chat(confirmCfg.getString("gui.name")))) {
                e.setCancelled(true);
                for (String key : confirmCfg.getConfigurationSection("items").getKeys(false)) {
                    String path = "items." + key + ".";
                    if (confirmCfg.getInt(path + "slot") - 1 == e.getSlot()) {
                        if (confirmCfg.getString(path + "type").equals("PLACEHOLDER"))
                            return;
                        String type1 = confirmCfg.getString(path + "type");
                        if (type1.equals("CONFIRM")) {
                            Types type = this.getType.get(p);
                            p.closeInventory();
                            if (type.equals(Types.BAN))
                                new BanPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            if (type.equals(Types.MUTE)) {
                                new MutePlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            } else if (type.equals(Types.KICK)) {
                                new KickPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.plugin);
                            } else if (type.equals(Types.WARN)) {
                                new WarnPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            } else if (type.equals(Types.TEMPWARN)) {
                                new WarnPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            }
                            if (type.equals(Types.TEMPBAN)) {
                                new BanPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            } else if (type.equals(Types.TEMPMUTE)) {
                                new MutePlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.getTime.get(p), this.plugin);
                            } else if (type.equals(Types.UNWARN)) {
                                new UnwarnPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.plugin);
                            } else if (type.equals(Types.UNBAN)) {
                                new UnbanPlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.plugin);
                            } else if (type.equals(Types.UNMUTE)) {
                                new UnmutePlayer(p, this.getTarget.get(p), this.getReason.get(p), ((Boolean)this.getSilent.get(p)).booleanValue(), this.plugin);
                            }
                        }
                        if (type1.equals("CANCEL")) {
                            p.closeInventory();
                            p.sendMessage(Utils.chat("&cYou've cancelled the punishment."));
                            return;
                        }
                        if (type1.equals("PLACEHOLDER"))
                            return;
                    }
                }
            }
        }
    }

    public void setMapFromCmd(Player p, OfflinePlayer target) {
        if (this.getTarget.containsKey(p)) {
            this.getTarget.replace(p, target);
        } else {
            this.getTarget.put(p, target);
        }
    }

    public ArrayList<Player> getReasonChatInputs() {
        return this.isReasonChatInput;
    }

    public ArrayList<Player> getTimeChatInputs() {
        return this.isTimeChatInput;
    }
}
