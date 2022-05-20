package pl.virtual.api.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Map;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.event.Event;
import pl.virtual.api.ServerPlugin;
import org.bukkit.entity.Player;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.Collection;

public final class ChatUtil
{
    public static String iiIi;
    public static String nmsver;
    
    static {
        ChatUtil.nmsver = Bukkit.getServer().getClass().getPackage().getName();
        ChatUtil.nmsver = ChatUtil.nmsver.substring(ChatUtil.nmsver.lastIndexOf(".") + 1);
    }
    
    public static Location locFromString(final String str) {
        final String[] str2loc = str.split(":");
        final Location loc = new Location((World) Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0, 0.0f, 0.0f);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        loc.setYaw(Float.parseFloat(str2loc[3]));
        loc.setPitch(Float.parseFloat(str2loc[4]));
        return loc;
    }
    
    public static ItemStack getItemStackFromString(final String itemstack) {
        final String[] splits = itemstack.split("@");
        final String type = splits[0];
        final String data = (splits.length == 2) ? splits[1] : null;
        if (data == null) {
            return new ItemStack(Material.getMaterial(type), 1);
        }
        return new ItemStack(Material.getMaterial(type), 1, (short)Integer.parseInt(data));
    }
    
    public static String locToString(final double x, final double y, final double z) {
        return String.valueOf(x) + ":" + y + ":" + z + ":" + 0.0f + ":" + 0.0f;
    }
    
    public static String locToString(final Location loc) {
        return String.valueOf(loc.getX()) + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
    }
    
    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        s.replace("$gp", "&8[&4&lGILDIE&8] ");
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static Collection<String> fixColor(final Collection<String> collection) {
        final Collection<String> local = new ArrayList<String>();
        for (final String s : collection) {
            local.add(fixColor(s));
        }
        return local;
    }
    
    public static int floor(final double value) {
        final int i = (int)value;
        return (value < i) ? (i - 1) : i;
    }
    
    public static double round(final double value, final int decimals) {
        final double p = Math.pow(10.0, decimals);
        return Math.round(value * p) / p;
    }
    
    public static String[] fixColor(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = fixColor(array[i]);
        }
        return array;
    }
    
    public static boolean sendMessage(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMessage(sender, message);
        }
        return permission != null && permission != "" && sender.hasPermission(permission) && sendMessage(sender, message);
    }
    
    public static boolean sendMessage(final CommandSender sender, final String message) {
        if (sender instanceof Player) {
            if (message != null || message != "") {
                sender.sendMessage(fixColor(message));
            }
        }
        else {
            sender.sendMessage(ChatColor.stripColor(fixColor(message)));
        }
        return true;
    }
    
    public static boolean error(final Player p, final String msg) {
        return sendMessage((CommandSender)p, "" + msg);
    }
    
    public static void sendTitleMessage(final Player player, String title, String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }
        title = title.replace("&", "§");
        subtitle = subtitle.replace("&", "§");
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
    
    public static void sendActionBar(final Player player, final String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(fixColor(message)));
    }
    
    public static Boolean sendTitle(final Player p, final String t, final String s) {
        p.sendTitle(fixColor(t), fixColor(s));
        return false;
    }
    
    public static Boolean sendTitle(final Collection<? extends Player> p, final String t, final String s) {
        p.stream().forEach(cp -> sendTitle(cp, t, s));
        return false;
    }
    public static Boolean sendActionBar(final Collection<? extends Player> p, final String m) {
        p.stream().forEach(cp -> sendActionBar(cp, m));
        return false;
    }
    
    
    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);
        if (duration >= 0) {
            new BukkitRunnable() {
                public void run() {
                    ChatUtil.sendActionBar(player, "");
                }
            }.runTaskLater((Plugin)ServerPlugin.getPlugin(), (long)(duration + 1));
        }
        while (duration > 60) {
            duration -= 60;
            final int sched = duration % 60;
            new BukkitRunnable() {
                public void run() {
                    ChatUtil.sendActionBar(player, message);
                }
            }.runTaskLater((Plugin)ServerPlugin.getPlugin(), (long)sched);
        }
    }
    
    public static void removeItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.removeItem(items);
        for (Map.Entry<Integer, ItemStack> entry : notStored.entrySet()) {}
    }
    
    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }
    
    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message, final String permission) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message, permission);
        }
        return true;
    }
    
    public static boolean containsIgnoreCase(final String[] array, final String element) {
        for (final String s : array) {
            if (s.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }
    
    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Material getMaterial(final String materialName) {
        Material returnMaterial = null;
        if (isInteger(materialName)) {
            final String id = String.valueOf(materialName);
            returnMaterial = Material.getMaterial(id);
        }
        else {
            returnMaterial = Material.matchMaterial(materialName);
        }
        return returnMaterial;
    }
    
    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }
    
    public static Player getDamager(final EntityDamageByEntityEvent e) {
        final Entity damager = e.getDamager();
        if (damager instanceof Player) {
            return (Player)damager;
        }
        if (damager instanceof Projectile) {
            final Projectile p = (Projectile)damager;
            if (p.getShooter() instanceof Player) {
                return (Player)p.getShooter();
            }
        }
        return null;
    }
    
    public static boolean isAlphaNumeric(final String s) {
        return s.matches("^[a-zA-Z0-9_]*$");
    }
    
    public static boolean isFloat(final String string) {
        return Pattern.matches("([0-9]*)\\.([0-9]*)", string);
    }
    
    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }
    
    public static Inventory newInventory(final int slot, final String name) {
        return Bukkit.createInventory((InventoryHolder)null, slot, ChatUtil.fixColor(name));
    }
}
