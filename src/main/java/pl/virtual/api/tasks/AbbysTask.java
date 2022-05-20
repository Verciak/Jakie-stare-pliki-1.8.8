package pl.virtual.api.tasks;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;
 
public class AbbysTask
{
    public static Inventory inv;
    public static boolean open;
    public static List<ItemStack> items;
    
    static {
        AbbysTask.items = new ArrayList<ItemStack>();
        AbbysTask.inv = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.fixColor("&4&lOTCHLAN"));
    }
    
    public static void startTask() {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                AbbysTask.task();
            }
        }, 20L, 6500L);
    }
    
    public static void task() {
        int i = 0;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &95 &7minut"));
            }
        }, (long)(i * 20));
        i += 60;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &94 &7minuty"));
            }
        }, (long)(i * 20));
        i += 60;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &93 &7minuty"));
            }
        }, (long)(i * 20));
        i += 60;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &92 &7minuty"));
            }
        }, (long)(i * 20));
        i += 60;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &91 &7minute"));
            }
        }, (long)(i * 20));
        i += 30;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &930 &7sekund"));
            }
        }, (long)(i * 20));
        i += 10;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &910 &7sekund"));
            }
        }, (long)(i * 20));
        i += 5;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &95 &7sekund"));
            }
        }, (long)(i * 20));
        ++i;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &94 &7sekundy"));
            }
        }, (long)(i * 20));
        ++i;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &93 &7sekundy"));
            }
        }, (long)(i * 20));
        ++i;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &92 &7sekundy"));
            }
        }, (long)(i * 20));
        ++i;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otwarcie otchlani odbedzie sie za &91 &7sekunde"));
            }
        }, (long)(i * 20));
        ++i;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                AbbysTask.inv.clear();
                final World w = Bukkit.getWorld("world");
                for (final Entity entity : w.getEntities()) {
                    if (entity instanceof Item) {
                        AbbysTask.items.add(((Item)entity).getItemStack());
                        entity.remove();
                    }
                }
                for (int ix = 0; ix < 54; ++ix) {
                    if (AbbysTask.inv.getItem(ix) == null && !AbbysTask.items.isEmpty()) {
                        final ItemStack is = AbbysTask.items.get(0);
                        if (is != null) {
                            AbbysTask.inv.setItem(ix, is);
                            AbbysTask.items.remove(is);
                        }
                    }
                }
                AbbysTask.open = true;
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otchlan zostala otwarta. Zamkniecie nastapi za &930 &7sekund"));
            }
        }, (long)(i * 20));
        i += 29;
        Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&LOTCHLAN&8] &7Otchlan zostala zamknieta"));
                AbbysTask.open = false;
            }
        }, (long)(i * 20));
    }
}
