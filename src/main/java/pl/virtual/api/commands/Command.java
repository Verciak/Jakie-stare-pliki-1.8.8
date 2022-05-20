
package pl.virtual.api.commands;

import org.bukkit.command.CommandSender;

import pl.virtual.api.utils.ChatUtil;

import java.util.List;
import java.util.Arrays;

public abstract class Command extends org.bukkit.command.Command
{
    private final String name;
    private final String usage;
    private final String desc;
    private final String permission;
    
    public Command(final String name, final String desc, final String usage, final String permission, final String... aliases) {
        super(name, desc, usage, (List)Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }
    
    public boolean execute(final CommandSender sender, final String label, final String[] args) {
        if ((this.permission != null || this.permission != "") && !sender.hasPermission(this.permission)) {
            String msg = "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej";
            msg = msg.replace("{PERM}", this.getPermission());
            return ChatUtil.sendMessage(sender, msg);
        }
        return this.onExecute(sender, args);
    }
    
    public abstract boolean onExecute(final CommandSender p0, final String[] p1);
    
    public String getName() {
        return this.name;
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String getPermission() {
        return this.permission;
    }
}
