// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.drops;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

public interface DropData
{
    void breakBlock(final Block p0, final Player p1, final ItemStack p2);
    
    DropType getDropType();
}
