// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class SmietnikCommand extends PlayerCommand
{
    public SmietnikCommand() {
        super("smietnik", "smietnik", "/smietnik", "core.cmd.user", new String[] { "kosz" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 63, ChatUtil.fixColor("&4&lKosz"));
        p.openInventory(inv);
        return true;
    }
}
