// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.drops;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.DropFile;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.DropUtil;
import pl.virtual.api.utils.LevelUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Set;
import java.util.List;

public class RandomDropData implements DropData
{
    private static final List<Drop> drops;
    private static final Set<UUID> noCobble;
    private static final Set<UUID> isDropMessages;
    
    static {
        drops = new ArrayList<Drop>();
        noCobble = new HashSet<UUID>();
        isDropMessages = new HashSet<UUID>();
    }
    
    public static void changeNoCobble(final UUID uuid) {
        if (RandomDropData.noCobble.contains(uuid)) {
            RandomDropData.noCobble.remove(uuid);
        }
        else {
            RandomDropData.noCobble.add(uuid);
        }
    }
    
    public static boolean isDropMessages(final UUID uuid) {
        return RandomDropData.isDropMessages.contains(uuid);
    }
    
    public static void changeNoMsg(final UUID uuid) {
        if (RandomDropData.isDropMessages.contains(uuid)) {
            RandomDropData.isDropMessages.remove(uuid);
        }
        else {
            RandomDropData.isDropMessages.add(uuid);
        }
    }
    
    public static boolean isNoCobble(final UUID uuid) {
        return RandomDropData.noCobble.contains(uuid);
    }
    
    public RandomDropData() {
        RandomDropData.drops.clear();
        for (final String s : DropFile.getConfig().getConfigurationSection("random-drops").getKeys(false)) {
            final Drop d = new Drop(s);
            RandomDropData.drops.add(d);
        }
    }
    
    public static Drop getDropByName(final String name) {
        for (final Drop d : RandomDropData.drops) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }
    
    @Override
    public void breakBlock(final Block block, final Player player, final ItemStack item) {
        final List<ItemStack> drop = new ArrayList<ItemStack>();
        for (final Drop d : RandomDropData.drops) {
            final ItemStack itemDrop = d.getWhat().clone();
            int expDrop = d.getExp();
            if (!d.getFrom().equals((Object)block.getType())) {
                continue;
            }
            if (!d.getTools().contains(item.getType())) {
                continue;
            }
            if (!d.getBiomes().contains(block.getBiome())) {
                continue;
            }
            final int y = block.getLocation().getBlockY();
            if (y < d.getMinHeight()) {
                continue;
            }
            if (y > d.getMaxHeight()) {
                continue;
            }
            double chance = d.getChance();
            if (player.hasPermission("core.drop.vip")) {
                chance *= 1.25;
            }
            else if (player.hasPermission("core.drop.svip")) {
                chance *= 1.5;
            }
            final User u = UserManager.getUser(player);
            final Guild g = GuildManager.getGuild(player);
            if (Config.TURBO_DROP > System.currentTimeMillis() || (u != null && u.getTurboDrop() > System.currentTimeMillis()) || (g != null && g.getTurboDrop() > System.currentTimeMillis())) {
                chance *= 2.0;
            }
            double bonus = 0.0;
            if (u != null) {
                bonus = d.getChance() / 100.0 * (100.0 + u.getLvl() * 1.2) - d.getChance();
            }
            chance += bonus;
            if (!RandomUtil.getChance(chance)) {
                continue;
            }
            if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && d.isFortune()) {
                final int a = DropUtil.addFortuneEnchant((d.getMinAmount() == d.getMaxAmount()) ? d.getMinAmount() : RandomUtil.getRandInt(d.getMinAmount(), d.getMaxAmount()), item);
                itemDrop.setAmount(a);
                expDrop *= a;
            }
            if (!d.isDisabled(player.getUniqueId())) {
                drop.add(itemDrop);
            }
            player.giveExp(expDrop);
            if (u != null) {
                u.setExp(u.getExp() + expDrop);
            }
            LevelUtil.checkLevel(u);
            if (d.getMessage().equalsIgnoreCase("")) {
                continue;
            }
            String msg = d.getMessage();
            msg = msg.replace("{AMOUNT}", Integer.toString(itemDrop.getAmount()));
            msg = msg.replace("{EXP}", String.valueOf(Integer.toString(expDrop)) + (d.getDisabled().contains(player.getUniqueId()) ? " &c(off)" : ""));
            if (isDropMessages(player.getUniqueId())) {
                continue;
            }
            ChatUtil.sendActionBar(player, ChatUtil.fixColor(msg));
        }
        if (!RandomDropData.noCobble.contains(player.getUniqueId())) {
            drop.add(new ItemStack(item.containsEnchantment(Enchantment.SILK_TOUCH) ? Material.STONE : Material.COBBLESTONE, 1));
        }
        DropUtil.addItemsToPlayer(player, drop, block);
        DropUtil.recalculateDurability(player, item);
        block.setType(Material.AIR);
    }
    
    @Override
    public DropType getDropType() {
        return DropType.RANDOM_DROP;
    }
    
    public static List<Drop> getDrops() {
        return RandomDropData.drops;
    }
}
