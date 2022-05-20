// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.inventory.ItemStack;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RepairCommand extends PlayerCommand
{
    private static List<Material> items;
    private static final HashMap<UUID, Long> times;
    private static final HashMap<UUID, Long> times1;
    
    static {
    	times = new HashMap<UUID, Long>();
    	times1 = new HashMap<UUID, Long>();
        RepairCommand.items = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_SWORD, Material.WOODEN_PICKAXE, Material.BOW, Material.SHEARS, Material.FISHING_ROD, Material.FLINT_AND_STEEL, Material.CARROT_ON_A_STICK, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_SWORD, Material.IRON_PICKAXE, Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_SWORD, Material.GOLDEN_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SWORD, Material.STONE_PICKAXE, Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.IRON_BOOTS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.GOLDEN_BOOTS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.GOLDEN_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS);
    }
    
    public RepairCommand() {
        super("repair", "Naprawia przedmioty", "/repair", "core.cmd.repair", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length > 2) {
            return ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: &7/repair <all>");
        }
        if (args.length == 0) {
            if (!canRepair(p.getItemInHand())) {
                return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz naprawic tego przedmiotu");
            }
            p.getItemInHand().setDurability((short)0);
            return ChatUtil.sendMessage((CommandSender)p, "&7Pomyslnie naprawiles przedmiot");
        }
        else {
            if (args.length != 1) {
                return ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: &7/repair <all>");
            }
            if (!args[0].equalsIgnoreCase("all")) {
                return ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: &7/repair <all>");
            }
            if (!p.hasPermission("core.cmd.repairall")) {
                return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
            }
            final Long t = RepairCommand.times.get(p.getUniqueId());
            if (t != null && System.currentTimeMillis() - t < 20000L) {
                return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tej komendy mozesz uzyc co 20s");
            }
            RepairCommand.times.put(p.getUniqueId(), System.currentTimeMillis());
            int il = 0;
            for (int i = 0; i < p.getInventory().getSize(); ++i) {
                if (p.getInventory().getItem(i) != null) {
                    if (p.getInventory().getItem(i).getType() != Material.AIR) {
                        final ItemStack is = p.getInventory().getItem(i);
                        if (canRepair(is)) {
                            is.setDurability((short)0);
                            ++il;
                        }
                    }
                }
            }
            return ChatUtil.sendMessage((CommandSender)p, "&7Pomyslnie naprawiles wszystkie przedmioty (&f" + il + "&7)");
        }
    }
    
    public static boolean canRepair(final ItemStack is) {
        return RepairCommand.items.contains(is.getType());
    }
}
