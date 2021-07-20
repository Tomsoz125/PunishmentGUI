package me.tomsoz.punishmentgui.punishmentgui.Database;

import me.tomsoz.punishmentgui.punishmentgui.PunishmentGUI;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class Database {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:"+PunishmentGUI.getDbConnectionURL());
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "There was a problem whilst connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void initialiseDatabase() {
        Connection connection = getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS punishmentgui_logs(id INT NOT NULL PRIMARY KEY IDENTITY(1, 1), status VARCHAR(255) NOT NULL, time BIGINT(20) NOT NULL, target VARCHAR(255) NOT NULL, executor VARCHAR(255) NOT NULL, revoker VARCHAR(255) NULL, punishment_type VARCHAR(255) NOT NULL, duration VARCHAR(255) NOT NULL, reason VARCHAR(255) NOT NULL, revoke_time BIGINT(20) NULL, silent INT NOT NULL");
            statement.execute();
            connection.close();
        } catch(SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "There was an issue initialising the database.");
            e.printStackTrace();
        }
    }

    public static void addPunishment(String status, int time, String target, String executor, String revoker, String type, String duration, String reason, int revokeTime, int silent) {
        Connection connection = getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO punishmentgui_logs(status, time, target, executor, revoker, punishment_type, duration, reason, revoke_time, silent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, status);
            statement.setInt(2, time);
            statement.setString(3, target);
            statement.setString(4, executor);
            statement.setString(5, revoker);
            statement.setString(6, type);
            statement.setString(7, duration);
            statement.setString(8, reason);
            statement.setInt(9, revokeTime);
            statement.setInt(10, silent);
            statement.execute();
            connection.close();
        } catch(SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "There was an issue logging info to the database.");
            e.printStackTrace();
        }
    }
}
