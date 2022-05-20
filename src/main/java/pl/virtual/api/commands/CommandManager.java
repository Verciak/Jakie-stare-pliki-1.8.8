// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.SimplePluginManager;

import pl.virtual.api.utils.Reflection;

import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.util.HashMap;

public class CommandManager
{
    public static final HashMap<String, Command> commands;
    private static final Reflection.FieldAccessor<SimpleCommandMap> f;
    private static CommandMap cmdMap;
    
    static {
        commands = new HashMap<String, Command>();
        f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
        CommandManager.cmdMap = (CommandMap)CommandManager.f.get(Bukkit.getServer().getPluginManager());
    }
    
    public static void register(final Command cmd) {
        if (CommandManager.cmdMap == null) {
            CommandManager.cmdMap = (CommandMap)CommandManager.f.get(Bukkit.getServer().getPluginManager());
        }
        CommandManager.cmdMap.register(cmd.getName(), (org.bukkit.command.Command)cmd);
        CommandManager.commands.put(cmd.getName(), cmd);
    }
}
