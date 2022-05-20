package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;


import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;

import java.util.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.drops.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class StoneCommand extends PlayerCommand
{
    public StoneCommand() {
        super("stone", "Sprawdzanie % na drop_old.", "/stone", "core.cmd.user", new String[] { "drop", "surowce", "procent", "kamien" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        show1(sender);
        return true;
        }
    public static void show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&9&lDrop"));
        for (final Drop d : RandomDropData.getDrops()) {
            double chance = d.getChance();
            if (p.hasPermission("core.drop.svip")) {
                chance *= 1.20;
            }
            else if (p.hasPermission("core.drop.vip")) {
                chance *= 1.10;
            }
            else if (p.hasPermission("core.drop.dark")) {
                chance *= 1.25;
            }
            final User u = UserManager.getUser(p);
            final Guild g = GuildManager.getGuild(p);
            if (Config.TURBO_DROP > System.currentTimeMillis() || (u != null && u.getTurboDrop() > System.currentTimeMillis()) || (g != null && g.getTurboDrop() > System.currentTimeMillis())) {
                chance *= 2.0;
            }
            double bonus;
            if (u == null) {
                bonus = 0.0;
            }
            else {
                bonus = d.getChance() / 100.0 * (100.0 + u.getLvl() * 1.2) - d.getChance();
            }
            final ItemBuilder b = new ItemBuilder(d.getWhat().getType(), 1);
            b.setTitle(ChatUtil.fixColor("&9&l" + d.getName().toUpperCase()));
            b.addLore(ChatUtil.fixColor("&8» &7Szansa na drop: &9&l" + ChatUtil.round(chance, 3) + "&9&l%" + ""));
            b.addLore(ChatUtil.fixColor("&8» &7Wypada pomiedzy: &9&lY:" + d.getMinHeight() + " &fa &9&l" + d.getMaxHeight() + " &7poziomem"));
            b.addLore(ChatUtil.fixColor("&8» &7Wypada z: &9&l" + d.getFrom()));
            b.addLore(ChatUtil.fixColor("&8"));
            b.addLore(ChatUtil.fixColor("&8» &7Drop: " + (d.isDisabled(p.getUniqueId()) ? "&cOFF" : "&aON")));
            ChatUtil.fixColor(" ");
            b.addLore(ChatUtil.fixColor("&7"));
            inv.addItem(new ItemStack[] { b.build() });
        }
        final ItemBuilder wroc = new ItemBuilder(Material.BARRIER, 1).setTitle(ChatUtil.fixColor("&cWroc do poprzedniej strony"));
        final ItemBuilder on = new ItemBuilder(Material.LIME_DYE, 1).setTitle(ChatUtil.fixColor("&aWlacz Wszystkie Dropy"));
        final ItemBuilder off = new ItemBuilder(Material.RED_DYE, 1).setTitle(ChatUtil.fixColor("&cWylacz Wszystkie Dropy"));
        final ItemBuilder stone = new ItemBuilder(Material.ENDER_CHEST, 1).setTitle(ChatUtil.fixColor("&9&lCase z STONE")).addLore(ChatUtil.fixColor("&8» &7Dostepny: " + ((Config.STONE > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(Config.STONE)) : "&cNie")));
        final ItemBuilder cbl = new ItemBuilder(Material.COBBLESTONE).setTitle(ChatUtil.fixColor("&9&lCobbleStone")).addLore(ChatUtil.fixColor(" &8» &7Drop: &" + (RandomDropData.isNoCobble(p.getUniqueId()) ? "cOFF" : "aON")));
        final ItemBuilder wiad = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&9&lWiadomosci z Dropu")).addLore(ChatUtil.fixColor(" &8» &7Wiadomosci: &" + (RandomDropData.isDropMessages(p.getUniqueId()) ? "cOFF" : "aON")));
        inv.setItem(inv.getSize() - 9, on.build());
        inv.setItem(inv.getSize() - 10, stone.build());
        inv.setItem(inv.getSize() - 3, cbl.build());
        inv.setItem(inv.getSize() - 8, off.build());
        inv.setItem(inv.getSize() - 2, wiad.build());
        inv.setItem(inv.getSize() - 1, wroc.build());
        p.openInventory(inv);
    }
    
    public static void show1(final Player p) {
        final User u = UserManager.getUser(p);
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lDrop menu"));
        final ItemBuilder c = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&4&lDrop z DarkCase")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor(" &8» &7Kliknij, aby przejsc dalej!"));
        final ItemBuilder air2 = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&8\u2022"));
        final ItemBuilder cbl = new ItemBuilder(Material.COBBLESTONE).setTitle(ChatUtil.fixColor("&4&lCobbleStone")).addLore(ChatUtil.fixColor(" &8» &fDrop: &" + (RandomDropData.isNoCobble(p.getUniqueId()) ? "cWylaczony" : "aWlaczony")));
        final ItemBuilder stone = new ItemBuilder(Material.STONE).setTitle(ChatUtil.fixColor("&4&lDrop")).addLore(ChatUtil.fixColor(" &8» &7Kliknij, aby przejsc dalej!"));
        final ItemBuilder paczka = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&4&lDrop z DarkCase")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor(" &8» &7Kliknij, aby przejsc dalej!"));
        final ItemBuilder itemBuilder = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&LStatystyki"));
        for (final Map.Entry<Material, Integer> en : DropManager.getExps().entrySet()) {
            int exp = en.getValue();
            final Guild g = GuildManager.getGuild(p);
            if (Config.TURBO_EXP > System.currentTimeMillis() || (u != null && u.getTurboExp() > System.currentTimeMillis()) || (g != null && g.getTurboExp() > System.currentTimeMillis())) {
                exp *= 2;
            }
            itemBuilder.addLore(ChatUtil.fixColor("&8» &7" + en.getKey() + ": &c" + exp));
        }
        final Guild g2 = GuildManager.getGuild(p);
        final ItemBuilder turbo = new ItemBuilder(Material.DIAMOND_PICKAXE).setTitle(ChatUtil.fixColor("&4&l")).addLore(ChatUtil.fixColor("&4&LTURBODROP SERWEROWY")).addLore(ChatUtil.fixColor("&8» &7Aktywny: " + ((Config.TURBO_DROP > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(Config.TURBO_DROP)) : "&c\u2716")));
        turbo.addLore(ChatUtil.fixColor("&4&lTURBODROP DLA CIEBIE")).addLore(ChatUtil.fixColor("&8» &7Aktywny: " + ((u != null && u.getTurboDrop() > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(u.getTurboDrop())) : "&c\u2716")));
        inv.setItem(inv.getSize() - 2, air2.build());
        inv.setItem(inv.getSize() - 4, air2.build());
        inv.setItem(inv.getSize() - 5, stone.build());
        inv.setItem(inv.getSize() - 1, paczka.build());
        inv.setItem(inv.getSize() - 3, turbo.build());
        p.openInventory(inv);
    }
    
    public static void show4(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 27, ChatUtil.fixColor("&4&lDrop z DarkCase"));
        final ItemBuilder a2 = new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder air = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&8\u2022"));
        final ItemBuilder k = new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder g = new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder h = new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder kd = new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
        final ItemBuilder kd2 = new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).setTitle(ChatUtil.fixColor("&4&l6/3/3"));;
        final ItemBuilder s = new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1);
        final ItemBuilder bu = new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2);
        final ItemBuilder ref = new ItemBuilder(Material.GOLDEN_APPLE, 16);
        final ItemBuilder ref2 = new ItemBuilder(Material.GOLDEN_APPLE, 32);
        final ItemBuilder kox = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 2);
        final ItemBuilder kox4 = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 4);
        final ItemBuilder per = new ItemBuilder(Material.ENDER_PEARL, 4);
        final ItemBuilder diam1 = new ItemBuilder(Material.DIAMOND_BLOCK, 16);
        final ItemBuilder mr8 = new ItemBuilder(Material.MOSSY_STONE_BRICKS, 8).setTitle(ChatUtil.fixColor("&f&lCobbleX")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Postaw na ziemi aby otworzyc")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>"));
        final ItemBuilder mr = new ItemBuilder(Material.MOSSY_STONE_BRICKS, 16).setTitle(ChatUtil.fixColor("&f&lCobbleX")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Postaw na ziemi aby otworzyc")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>"));
        final ItemBuilder diam = new ItemBuilder(Material.DIAMOND_BLOCK, 32);
        final ItemBuilder dark = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&9&lCase")).setTitle(ChatUtil.fixColor("&9&lCase")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("&7Jest to magiczna skrzynka")).addLore(ChatUtil.fixColor("&7Skrywajaca w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Drop z &9&lCase")).addLore(ChatUtil.fixColor("&7mozesz sprawdzic pod &9/drop")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder bu2 = new ItemBuilder(Material.WITHER_SKELETON_SKULL, 1).setTitle(ChatUtil.fixColor("&4&lCZASZKA MROCZNEGO SZKIELETA"));
        final ItemBuilder bu3 = new ItemBuilder(Material.STICK, 16);
        final ItemBuilder slim = new ItemBuilder(Material.SLIME_BLOCK, 4);
        final ItemBuilder vo = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBO 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vo2 = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vo1 = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder wroc = new ItemBuilder(Material.BARRIER, 1, (short)14).setTitle(ChatUtil.fixColor("&cWroc do poprzedniej strony"));
        inv.setItem(inv.getSize() - 27, a2.build());
        inv.setItem(inv.getSize() - 26, k.build());
        inv.setItem(inv.getSize() - 25, g.build());
        inv.setItem(inv.getSize() - 24, h.build());
        inv.setItem(inv.getSize() - 23, ref.build());
        inv.setItem(inv.getSize() - 22, ref2.build());
        inv.setItem(inv.getSize() - 21, kox.build());
        inv.setItem(inv.getSize() - 20, kox4.build());
        inv.setItem(inv.getSize() - 19, bu.build());
        inv.setItem(inv.getSize() - 18, s.build());
        inv.setItem(inv.getSize() - 17, diam1.build());
        inv.setItem(inv.getSize() - 16, diam.build());
        inv.setItem(inv.getSize() - 15, dark.build());
        inv.setItem(inv.getSize() - 14, bu2.build());
        inv.setItem(inv.getSize() - 13, kd.build());
        inv.setItem(inv.getSize() - 12, kd2.build());
        inv.setItem(inv.getSize() - 11, bu3.build());
        inv.setItem(inv.getSize() - 10, mr8.build());
        inv.setItem(inv.getSize() - 9, mr.build());
        inv.setItem(inv.getSize() - 8, vo.build());
        inv.setItem(inv.getSize() - 7, vo2.build());
        inv.setItem(inv.getSize() - 6, vo1.build());
        inv.setItem(inv.getSize() - 5, per.build());
        inv.setItem(inv.getSize() - 4, slim.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, air.build());
        inv.setItem(inv.getSize() - 1, wroc.build());
        p.openInventory(inv);
    }
    
    public static void show5(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 27, ChatUtil.fixColor("&4&lDrop z DarkCase"));
        final ItemBuilder a2 = new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder air = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&8\u2022"));
        final ItemBuilder k = new ItemBuilder(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder g = new ItemBuilder(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder h = new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchantment(Enchantment.DURABILITY, 2);
        final ItemBuilder kd = new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
        final ItemBuilder kd2 = new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).setTitle(ChatUtil.fixColor("&4&l6/3/3"));;
        final ItemBuilder s = new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3).addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1);
        final ItemBuilder bu = new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2);
        final ItemBuilder ref = new ItemBuilder(Material.GOLDEN_APPLE, 16);
        final ItemBuilder ref2 = new ItemBuilder(Material.GOLDEN_APPLE, 32);
        final ItemBuilder kox = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 2);
        final ItemBuilder kox4 = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 4);
        final ItemBuilder per = new ItemBuilder(Material.ENDER_PEARL, 4);
        final ItemBuilder diam1 = new ItemBuilder(Material.DIAMOND_BLOCK, 16);
        final ItemBuilder mr8 = new ItemBuilder(Material.MOSSY_STONE_BRICKS, 8).setTitle(ChatUtil.fixColor("&4&lMARMUR")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Postaw na ziemi aby otworzyc")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>"));
        final ItemBuilder mr = new ItemBuilder(Material.MOSSY_STONE_BRICKS, 16).setTitle(ChatUtil.fixColor("&4&lMARMUR")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Postaw na ziemi aby otworzyc")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>"));
        final ItemBuilder diam = new ItemBuilder(Material.DIAMOND_BLOCK, 32);
        final ItemBuilder dark = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&8&lDarkCase")).setTitle(ChatUtil.fixColor("&8&lDarkCase")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor("&7Jest to magiczna skrzynka")).addLore(ChatUtil.fixColor("&7Skrywajaca w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Drop z &8&lDarkCase")).addLore(ChatUtil.fixColor("&7mozesz sprawdzic pod &8/drop")).addLore(ChatUtil.fixColor("&8<&8&m------------------------&8>")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder bu2 = new ItemBuilder(Material.WITHER_SKELETON_SKULL, 1).setTitle(ChatUtil.fixColor("&4&lCZASZKA MROCZNEGO SZKIELETA"));
        final ItemBuilder bu3 = new ItemBuilder(Material.STICK, 16);
        final ItemBuilder slim = new ItemBuilder(Material.SLIME_BLOCK, 4);
        final ItemBuilder vo = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBO 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vo2 = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vo1 = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder wroc = new ItemBuilder(Material.BARRIER, 1, (short)14).setTitle(ChatUtil.fixColor("&cWroc do poprzedniej strony"));
        inv.setItem(inv.getSize() - 27, a2.build());
        inv.setItem(inv.getSize() - 26, k.build());
        inv.setItem(inv.getSize() - 25, g.build());
        inv.setItem(inv.getSize() - 24, h.build());
        inv.setItem(inv.getSize() - 23, ref.build());
        inv.setItem(inv.getSize() - 22, ref2.build());
        inv.setItem(inv.getSize() - 21, kox.build());
        inv.setItem(inv.getSize() - 20, kox4.build());
        inv.setItem(inv.getSize() - 19, bu.build());
        inv.setItem(inv.getSize() - 18, s.build());
        inv.setItem(inv.getSize() - 17, diam1.build());
        inv.setItem(inv.getSize() - 16, diam.build());
        inv.setItem(inv.getSize() - 15, dark.build());
        inv.setItem(inv.getSize() - 14, bu2.build());
        inv.setItem(inv.getSize() - 13, kd.build());
        inv.setItem(inv.getSize() - 12, kd2.build());
        inv.setItem(inv.getSize() - 11, bu3.build());
        inv.setItem(inv.getSize() - 10, mr8.build());
        inv.setItem(inv.getSize() - 9, mr.build());
        inv.setItem(inv.getSize() - 8, vo.build());
        inv.setItem(inv.getSize() - 7, vo2.build());
        inv.setItem(inv.getSize() - 6, vo1.build());
        inv.setItem(inv.getSize() - 5, per.build());
        inv.setItem(inv.getSize() - 4, slim.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, air.build());
        inv.setItem(inv.getSize() - 1, wroc.build());
        p.openInventory(inv);
    }
    
    public static void showcx(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 9, ChatUtil.fixColor("&4&lDrop z CobbleX"));
        final ItemBuilder air2 = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&f*"));
        final ItemBuilder h = new ItemBuilder(Material.BOOKSHELF, 5);
        final ItemBuilder bu = new ItemBuilder(Material.ENDER_PEARL);
        final ItemBuilder m = new ItemBuilder(Material.GOLD_BLOCK, 3);
        final ItemBuilder tnt = new ItemBuilder(Material.SLIME_BALL, 3);
        final ItemBuilder per = new ItemBuilder(Material.GOLDEN_APPLE, 1);
        final ItemBuilder lav = new ItemBuilder(Material.END_STONE, 5);
        final ItemBuilder wroc = new ItemBuilder(Material.BARRIER, 1).setTitle(ChatUtil.fixColor("&cWroc do poprzedniej strony"));
        inv.setItem(inv.getSize() - 9, h.build());
        inv.setItem(inv.getSize() - 8, bu.build());
        inv.setItem(inv.getSize() - 7, m.build());
        inv.setItem(inv.getSize() - 6, per.build());
        inv.setItem(inv.getSize() - 5, lav.build());
        inv.setItem(inv.getSize() - 4, tnt.build());
        inv.setItem(inv.getSize() - 3, air2.build());
        inv.setItem(inv.getSize() - 2, air2.build());
        inv.setItem(inv.getSize() - 1, wroc.build());
        p.openInventory(inv);
    }
}
