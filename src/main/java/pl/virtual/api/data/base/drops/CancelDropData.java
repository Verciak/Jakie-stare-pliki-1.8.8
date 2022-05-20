// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.drops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

public class CancelDropData implements DropData
{
    @Override
    public void breakBlock(final Block block, final Player player, final ItemStack item) {
        block.setType(Material.AIR);
    }
    
    @Override
    public DropType getDropType() {
        return DropType.CANCEL_DROP;
    }
}
