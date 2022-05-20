package pl.virtual.api.API;

import org.bukkit.configuration.file.*;

import pl.virtual.api.ServerPlugin;

import java.io.*;
import java.lang.reflect.*;

public class Lang
{
    public static String MSG_USAGE;
    private static File file;
    private static FileConfiguration c;
    
    static {
        Lang.file = new File(ServerPlugin.getPlugin().getDataFolder(), "lang.yml");
        Lang.c = null;
        Lang.MSG_USAGE = "&cPoprawne uzycie: &7{USAGE}";
    }
    
    public static String USE(final String use) {
        return Lang.MSG_USAGE.replace("{USAGE}", use);
    }
    
    public static void loadLang() {
        try {
            if (!Lang.file.exists()) {
                Lang.file.getParentFile().mkdirs();
                final InputStream is = ServerPlugin.getPlugin().getResource(Lang.file.getName());
                if (is != null) {
                    Util.copy(is, Lang.file);
                }
            }
            Lang.c = (FileConfiguration)YamlConfiguration.loadConfiguration(Lang.file);
            Field[] fields;
            for (int length = (fields = Lang.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (Lang.c.isSet("lang." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", "."))) {
                    f.set(null, Lang.c.get("lang." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", ".")));
                }
            }
        }
        catch (Exception e) {
            Util.exception(e);
        }
    }
    
    public static void saveLang() {
        try {
            Field[] fields;
            for (int length = (fields = Lang.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                Lang.c.set("lang." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", "."), f.get(null));
            }
            Lang.c.save(Lang.file);
        }
        catch (Exception e) {
            Util.exception(e);
        }
    }
    
    public static void reloadLang() {
        loadLang();
        saveLang();
    }
}
