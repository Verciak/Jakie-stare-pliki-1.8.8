package pl.virtual.api.managers;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import pl.virtual.api.utils.ChatUtil;

public class BlokiManager
{
    public static void replacer(final Player p, final Material item, final Material block) {
        if (!p.getInventory().containsAtLeast(new ItemStack(item), 9)) {
            p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz takich sztabek"));
            return;
        }
        Boolean b = null;
        for (int x = 0; x < 256; ++x) {
            if (!p.getInventory().containsAtLeast(new ItemStack(item), 9)) {
                if (b) {
                    p.sendMessage(ChatUtil.fixColor("&a&lSukces &7Zmieniles swoje sztabki na bloki"));
                }
                return;
            }
            p.getInventory().removeItem(new ItemStack[] { new ItemStack(item, 9) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(block) });
            b = true;
        }
        if (!p.getInventory().containsAtLeast(new ItemStack(item), 9)) {
            return;
        }
        if (!p.getInventory().containsAtLeast(new ItemStack(item), 9)) {
            return;
        }
        Boolean b2 = null;
        for (int x = 0; x < 256; ++x) {
            if (!p.getInventory().containsAtLeast(new ItemStack(item), 9)) {
                if (b2) {
                    p.sendMessage(ChatUtil.fixColor("&a&lSukces &7Zmieniles swoje sztabki na bloki"));
                }
                return;
            }
            p.getInventory().removeItem(new ItemStack[] { new ItemStack(item, 9) });
            p.getInventory().addItem(new ItemStack[] { new ItemStack(block) });
            b2 = true;
        }
    }
}
