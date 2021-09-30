package me.tomsoz.punishmentgui.punishmentgui.Database;

import me.tomsoz.punishmentgui.punishmentgui.Misc.Utils;
import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public class Database {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:"+PunishmentGUI.getConnectionURL());
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cFailed to connect to the database!"));
            e.printStackTrace();
        }
        return connection;
    }

    public static void initialiseDatabase() {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "CREATE TABLE IF NOT EXISTS punishmentsgui_logs(id INT PRIMARY KEY AUTO_INCREMENT, status VARCHAR(255) NOT NULL, time BIGINT(20) NOT NULL, target VARCHAR(255) NOT NULL, executor VARCHAR(255) NOT NULL, revoker VARCHAR(255) NULL, punishment_type VARCHAR(255) NOT NULL, duration VARCHAR(255) NOT NULL, reason VARCHAR(255) NOT NULL, revoke_time BIGINT(20) NULL, silent INT NOT NULL);";
        try {
            statement = connection.prepareStatement(sql);
            statement.execute();
            connection.close();
        } catch(SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst initialising the database!"));
            e.printStackTrace();
        }
    }

    public static void logPunishment(String status, long time, String target, String executor, String revoker, String type, String duration, String reason, long revokeTime, int silent) {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "INSERT INTO punishmentsgui_logs(status, time, target, executor, revoker, punishment_type, duration, reason, revoke_time, silent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setLong(2, time);
            statement.setString(3, target);
            statement.setString(4, executor);
            statement.setString(5, revoker);
            statement.setString(6, type);
            statement.setString(7, duration);
            statement.setString(8, reason);
            statement.setLong(9, revokeTime);
            statement.setInt(10, silent);
            statement.execute();
            connection.close();
        } catch(SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst adding a value to the database: "));
            e.printStackTrace();
        }
    }

    public static void logRevoke(String target, String revoker, long revokeTime, String type) {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "UPDATE punishmentsgui_logs SET revoker = '"+revoker+"', revoke_time = "+revokeTime+", status = 'revoked' WHERE target='"+target+"' AND status='active' AND punishment_type='"+type+"'";
        try {
            statement = connection.prepareStatement(sql);
            statement.execute();
            connection.close();
        } catch(SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst adding a value to the database: "));
            e.printStackTrace();
        }
    }
    public static boolean isPlayerBanned(OfflinePlayer p) {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "SELECT * FROM punishmentsgui_logs";
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            boolean result = false;
            while (rows.next()) {
                if (rows.getString("target").equals(p.getUniqueId().toString())&&
                        rows.getString("punishment_type").equals("BAN")&&
                        rows.getString("status").equals("active")) {
                    result = true;
                }
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst reading the database: "));
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isPlayerMuted(OfflinePlayer p) {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "SELECT * FROM punishmentsgui_logs";
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            boolean result = false;
            while (rows.next()) {
                if (rows.getString("target").equals(p.getUniqueId().toString())&&
                        rows.getString("punishment_type").equals("MUTE")&&
                        rows.getString("status").equals("active")) {
                    result = true;
                }
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst reading the database: "));
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isPlayerWarned(OfflinePlayer p) {
        Connection connection = getConnection();
        PreparedStatement statement;
        String sql = "SELECT * FROM punishmentsgui_logs";
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            boolean result = false;
            while (rows.next()) {
                if (rows.getString("target").equals(p.getUniqueId().toString())&&
                        rows.getString("punishment_type").equals("WARN")&&
                        rows.getString("status").equals("active")) {
                    result = true;
                }
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occurred whilst reading the database: "));
            e.printStackTrace();
            return false;
        }
    }
}
