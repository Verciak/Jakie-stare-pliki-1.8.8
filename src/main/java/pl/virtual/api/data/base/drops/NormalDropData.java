// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.drops;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.CropState;
import org.bukkit.material.Crops;
import org.bukkit.material.CocoaPlant;
import org.bukkit.NetherWartsState;
import org.bukkit.material.NetherWarts;

import pl.virtual.api.utils.DropUtil;
import pl.virtual.api.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

public class NormalDropData implements DropData
{
    @Override
    public void breakBlock(final Block block, final Player player, final ItemStack is) {
        final List<ItemStack> drops = this.getDrops(block, is);
        DropUtil.addItemsToPlayer(player, drops, block);
        DropUtil.recalculateDurability(player, is);
        block.setType(Material.AIR);
    }
    
    public List<ItemStack> getDrops(final Block block, final ItemStack item) {
        final List<ItemStack> items = new ArrayList<ItemStack>();
        final Material type = block.getType();
        int amount = 1;
        short data = block.getData();
        switch (type) {
            case PUMPKIN_STEM: {
                items.add(new ItemStack(Material.PUMPKIN_SEEDS, 1));
                break;
            }
            case MELON_STEM: {
                items.add(new ItemStack(Material.MELON_SEEDS, 1));
                break;
            }
            case REDSTONE_WIRE:
            case LEVER:
            case REDSTONE_ORE:
            case STONE_BUTTON:
            case TRIPWIRE:
            case DAYLIGHT_DETECTOR:
            case IRON_DOOR: {
                items.addAll(block.getDrops(item));
                break;
            }
            default: {
                if (item.containsEnchantment(Enchantment.SILK_TOUCH) && block.getType().isBlock()) {
                    items.add(new ItemStack(block.getType(), 1, (short)block.getData()));
                    break;
                }
                items.addAll(block.getDrops(item));
                break;
            }
        }
        return items;
    }
    
    @Override
    public DropType getDropType() {
        return DropType.NORMAL_DROP;
    }
}
