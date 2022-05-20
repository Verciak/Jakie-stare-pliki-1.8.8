// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import java.util.Iterator;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemUtil
{
    public static ItemStack getPlayerHead(final String name) {
        final ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta)itemStack.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &7Glowa gracza &c" + name + " &7&k:&f&k:&7&k:&6&l&l"));
        itemStack.setItemMeta((ItemMeta)meta);
        return itemStack;
    }
    
    public static String itemsToString(ItemStack[] items)
    {
      try
      {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(serializeItemStack(items));
        oos.flush();
        return DatatypeConverter.printBase64Binary(bos.toByteArray());
      }
      catch (Exception e)
      {
      }
      return "";
    }
    
    public static ItemStack[] stringToItems(String s)
    {
      try
      {
        ByteArrayInputStream bis = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(s));
        ObjectInputStream ois = new ObjectInputStream(bis);
        return deserializeItemStack((Map[])ois.readObject());
      }
      catch (Exception e)
      {
      }
      return null;
    }
    
    private static Map<String, Object>[] serializeItemStack(ItemStack[] items)
    {
      Map<String, Object>[] result = (Map[])new Map[items.length];
      for (int i = 0; i < items.length; i++)
      {
        ItemStack is = items[i];
        if (is == null)
        {
          result[i] = new HashMap();
        }
        else
        {
          result[i] = is.serialize();
          if (is.hasItemMeta()) {
            result[i].put("meta", is.getItemMeta().serialize());
          }
        }
      }
      return result;
    }
    
    private static ItemStack[] deserializeItemStack(Map<String, Object>[] map)
    {
      ItemStack[] items = new ItemStack[map.length];
      for (int i = 0; i < items.length; i++)
      {
        Map<String, Object> s = map[i];
        if (s.size() == 0) {
          items[i] = null;
        } else {
          try
          {
            if (s.containsKey("meta"))
            {
              Map<String, Object> im = new HashMap((Map)s.remove("meta"));
              im.put("==", "ItemMeta");
              ItemStack is = ItemStack.deserialize(s);
              is.setItemMeta((ItemMeta)ConfigurationSerialization.deserializeObject(im));
              items[i] = is;
            }
            else
            {
              items[i] = ItemStack.deserialize(s);
            }
          }
          catch (Exception e)
          {
            items[i] = null;
          }
        }
      }
      return items;
    }
    
    public static List<ItemStack> getItems(final String string, final int modifier) {
        final List<ItemStack> items = new ArrayList<ItemStack>();
        String[] split2;
        for (int length = (split2 = string.split(";")).length, i = 0; i < length; ++i) {
            final String s = split2[i];
            final String[] split = s.split("-");
            final String id = String.valueOf(split[0].split(":")[0]);
            final int data = Integer.parseInt(split[0].split(":")[1]);
            final int amount = Integer.parseInt(split[1].split(":")[0]) * modifier;
            final String name = split[1].split(":")[1];
            final ItemStack is = new ItemStack(Material.getMaterial(id), amount, (short)data);
            final ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(name);
            is.setItemMeta(meta);
            items.add(is);
        }
        return items;
    }
    
    public static boolean checkItems(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        for (final ItemStack is : items) {
            final ItemStack item = new ItemStack(Material.getMaterial(is.getType().name()), is.getAmount(), (short)is.getData().getData());
            if (!p.getInventory().containsAtLeast(item, item.getAmount())) {
                return false;
            }
        }
        return true;
    }
    
    public static int getItem(final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        return items.size();
    }
    
    public static String getItem1(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz wszystkich itemow");
        for (final ItemStack is : items) {
            final String id = is.getType().name();
            final int data = is.getData().getData();
            final int amount = is.getAmount();
            final int ii = getItemAmount(Material.getMaterial(id), p, (short)data);
        }
        return null;
    }
    
    public static String getItem(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz wszystkich itemow");
        ChatUtil.sendMessage((CommandSender)p, "&7Brakuje ci:");
        for (final ItemStack is : items) {
            final String id = is.getType().name();
            final int data = is.getData().getData();
            final int amount = is.getAmount();
            final int ii = getItemAmount(Material.getMaterial(id), p, (short)data);
            ChatUtil.sendMessage((CommandSender)p, String.valueOf(color(ii, amount)) + " &c- " + is.getItemMeta().getDisplayName() + " " + ii + "/" + amount + " - " + "\n");
        }
        return null;
    }
    
    public static String getItemJoin(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        String s = "&7Aby dolaczyc do gildii potrzbujesz &c";
        for (final ItemStack is : items) {
            final String id = is.getType().name();
            final int data = is.getData().getData();
            final int amount = is.getAmount();
            final int ii = getItemAmount(Material.getMaterial(id), p, (short)data);
            s = String.valueOf(s) + is.getItemMeta().getDisplayName() + " " + amount + " &7sztuk";
        }
        ChatUtil.sendMessage((CommandSender)p, s);
        return null;
    }
    
    public static void getItemGui(final String it, final int mod, final Player p, final int window, final String windowname) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, window, ChatUtil.fixColor(windowname));
        final List<ItemStack> items = getItems(it, mod);
        for (final ItemStack is : items) {
            final String id = is.getType().name();
            final int data = is.getData().getData();
            final int amount = is.getAmount();
            final int ii = getItemAmount(Material.getMaterial(id), p, (short)data);
            final int iiall = ii;
            final ItemBuilder builder = new ItemBuilder(Material.getMaterial(id), amount, (short)data);
            final ItemBuilder air = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&8&l#"));
            inv.setItem(0, air.build());
            inv.setItem(1, air.build());
            inv.setItem(2, air.build());
            inv.setItem(3, air.build());
            inv.setItem(4, air.build());
            inv.setItem(5, air.build());
            inv.setItem(6, air.build());
            inv.setItem(7, air.build());
            inv.setItem(8, air.build());
            inv.setItem(18, air.build());
            inv.setItem(19, air.build());
            inv.setItem(20, air.build());
            inv.setItem(21, air.build());
            inv.setItem(22, air.build());
            inv.setItem(23, air.build());
            inv.setItem(24, air.build());
            inv.setItem(25, air.build());
            inv.setItem(26, air.build());
            builder.setTitle(ChatUtil.fixColor(String.valueOf(color(ii, amount)) + is.getItemMeta().getDisplayName()));
            builder.addLore(ChatUtil.fixColor(""));
            builder.addLore(ChatUtil.fixColor("&4&lPOSIADANE ITEMY NA GILDIE"));
            builder.addLore(ChatUtil.fixColor(""));
            builder.addLore(ChatUtil.fixColor("&8» &7Itemy posiadanie w Eq &c" + ii + ""));
            builder.addLore(ChatUtil.fixColor(""));
            builder.addLore(ChatUtil.fixColor("&4&lPOSTEP W ZAKLADANIU GILDII"));
            builder.addLore(ChatUtil.fixColor(""));
            builder.addLore(ChatUtil.fixColor("&8» &7Postep w zakladaniu Gildii &c" + iiall + "&8/&c" + amount));
            builder.addLore(ChatUtil.fixColor("&8» &7Postep w Procentach &c" + iiall / amount * 100.0 + "&c%"));
            inv.addItem(new ItemStack[] { builder.build() });
        }
        p.openInventory(inv);
    }
    
    public static void removeItems(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        for (final ItemStack is : items) {
            final ItemStack item = new ItemStack(Material.getMaterial(is.getType().name()), is.getAmount(), (short)is.getData().getData());
            if (p.getInventory().containsAtLeast(item, item.getAmount())) {
                p.getInventory().removeItem(new ItemStack[] { item });
            }
        }
    }
    
    public static int getItemAmount(final Material material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }
    
    public static int getItemEnderchest(final Material material, final Player player, final short durability) {
        int Enderchest = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getEnderChest().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
            	Enderchest += itemStack.getAmount();
            }
        }
        return Enderchest;
    }
    
    private static String color(final int i, final int i2) {
        if (i >= i2) {
            return ChatUtil.fixColor("&c");
        }
        return ChatUtil.fixColor("&c");
    }
    
    public static void giveItemsToEnder(final Player p, final ItemStack... items) {
        final Inventory i = p.getEnderChest();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }
    
    public static int getAmountOfItem(final Material material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }
    
    public static int remove(final ItemStack base, final Player player, final int amount) {
        int actual = 0;
        int remaining = amount;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (actual == amount) {
                break;
            }
            if (itemStack != null && itemStack.getType().equals((Object)base.getType()) && itemStack.getDurability() == base.getDurability()) {
                if (remaining == 0) {
                    actual += itemStack.getAmount();
                    player.getInventory().remove(itemStack);
                }
                else if (itemStack.getAmount() >= amount) {
                    actual += itemStack.getAmount() - amount;
                    itemStack.setAmount(amount);
                    remaining = 0;
                }
                else {
                    final int add = itemStack.getAmount();
                    remaining -= add;
                    player.getInventory().remove(itemStack);
                    actual += add;
                }
            }
        }
        return actual;
    }
    
    public static boolean checkItemse(final List<ItemStack> items, final Player p) {
        for (final ItemStack item : items) {
            if (!p.getInventory().containsAtLeast(item, item.getAmount())) {
                return false;
            }
        }
        return true;
    }
    
    public static void removeItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.removeItem(items);
        for (Map.Entry<Integer, ItemStack> entry : notStored.entrySet()) {}
    }
    
    public static void removeItemsDepo(final Material material, final Player p, final short durability, int amount) {
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (amount > 0 && itemStack != null && itemStack.getType() == material && itemStack.getDurability() == durability) {
                if (itemStack.getAmount() > amount) {
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    amount = 0;
                }
                else {
                    amount -= itemStack.getAmount();
                    p.getInventory().removeItem(new ItemStack[] { itemStack });
                }
            }
        }
    }
    
    public static void removeItemse(final List<ItemStack> items, final Player player) {
        final Inventory inv = (Inventory)player.getInventory();
        final List<ItemStack> removes = new ArrayList<ItemStack>();
        for (final ItemStack item : items) {
            if (inv.containsAtLeast(item, item.getAmount())) {
                removes.add(item);
            }
        }
        if (removes.size() == items.size()) {
            for (final ItemStack item : items) {
                for (final ItemStack remove : removes) {
                    if (item.getType().equals((Object)remove.getType()) && item.getData().equals((Object)remove.getData())) {
                        inv.removeItem(new ItemStack[] { item });
                    }
                }
            }
        }
        removes.clear();
    }
}
