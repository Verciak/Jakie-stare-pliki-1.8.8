// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import java.io.InputStream;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.virtual.api.ServerPlugin;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class DropFile
{
    private static ServerPlugin plugin;
    private static FileConfiguration dropConfig;
    private static File dropConfigFile;
    
    static {
        DropFile.plugin = ServerPlugin.getPlugin();
        DropFile.dropConfig = null;
        DropFile.dropConfigFile = null;
    }

    public static void reloadConfig() {
        if (DropFile.dropConfigFile == null) {
            DropFile.dropConfigFile = new File(DropFile.plugin.getDataFolder(), "drops.yml");
        }
        DropFile.dropConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(DropFile.dropConfigFile);
        final InputStream defConfigStream = DropFile.plugin.getResource("drops.yml");
        if (defConfigStream != null) {
            final YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new File(DropFile.plugin.getDataFolder(), "drops.yml"));
            DropFile.dropConfig.setDefaults((Configuration)defConfig);
        }
    }
    
    public static FileConfiguration getConfig() {
        if (DropFile.dropConfig == null) {
            reloadConfig();
        }
        return DropFile.dropConfig;
    }
    
    public static void saveConfig() {
        if (DropFile.dropConfig == null || DropFile.dropConfigFile == null) {
            return;
        }
        try {
            getConfig().save(DropFile.dropConfigFile);
        }
        catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config to " + DropFile.dropConfigFile, ex);
        }
    }
    
    public static void saveDefaultConfig() {
        if (DropFile.dropConfigFile == null) {
            DropFile.dropConfigFile = new File(DropFile.plugin.getDataFolder(), "drops.yml");
        }
        if (!DropFile.dropConfigFile.exists()) {
            DropFile.plugin.saveResource("drops.yml", false);
        }
    }
}
