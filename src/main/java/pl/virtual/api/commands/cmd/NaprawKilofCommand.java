package pl.virtual.api.commands.cmd;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

public class NaprawKilofCommand extends PlayerCommand
{
    private static final HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    public NaprawKilofCommand() {
        super("naprawkilof", "naprawkiof", "/naprawkilof", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final ItemStack is = p.getItemInHand();
        if (is.getType() != Material.DIAMOND_PICKAXE && is.getType() != Material.IRON_PICKAXE && is.getType() != Material.WOODEN_PICKAXE && is.getType() != Material.STONE_PICKAXE && is.getType() != Material.GOLDEN_PICKAXE) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten przedmiot nie jest kilofem");
        }
        if (is.getDurability() == 1) {
            return ChatUtil.sendMessage((CommandSender)p, "&a&lSukces &7Ten przedmiot jest naprawiony");
        }
        final Long t = NaprawKilofCommand.times.get(p.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 43200000L) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Przedmiot mozesz naprawiac co 12 godzin");
        }
        NaprawKilofCommand.times.put(p.getUniqueId(), System.currentTimeMillis());
        is.setDurability((short)0);
        return ChatUtil.sendMessage((CommandSender)p, "&a&lSukces &7Naprawiony kilof");
    }
}