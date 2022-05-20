// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.virtual.api.ServerPlugin;

public class CraftingUtil
{
    public static String invName;
    public static String invName1;
    public static String invName2;
    public static String invName3;
    public static String invName4;
    public static String invName5;
    public static ItemStack a;
    public static ItemStack b;
    public static ItemStack c;
    public static ItemStack f;
    public static ItemStack k;
    public static ItemStack d;
    public static ItemStack e;
    public static ItemStack el;
    public static ItemStack back;
	private static ItemStack b1;
	private static String invName6;
    
    static {
        CraftingUtil.invName = ChatUtil.fixColor("&4&lCrafting GUI");
        CraftingUtil.invName1 = ChatUtil.fixColor("&4&lCrafting 1/6");
        CraftingUtil.invName2 = ChatUtil.fixColor("&4&lCrafting 2/6");
        CraftingUtil.invName3 = ChatUtil.fixColor("&4&lCrafting 3/6");
        CraftingUtil.invName4 = ChatUtil.fixColor("&4&lCrafting 4/6");
        CraftingUtil.invName5 = ChatUtil.fixColor("&4&lCrafting 5/6");
        CraftingUtil.invName6 = ChatUtil.fixColor("&4&lCrafting 6/6");

        CraftingUtil.a = new ItemBuilder(Material.END_STONE).setTitle(ChatUtil.fixColor("&6&lStoniarka")).build();
        CraftingUtil.b = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&6&LEnderchest")).build();
        CraftingUtil.c = new ItemBuilder(Material.DARK_PRISMARINE, 4).setTitle(ChatUtil.fixColor("&6&lBoyFarmer")).build();
        CraftingUtil.f = new ItemBuilder(Material.PRISMARINE, 4).setTitle(ChatUtil.fixColor("&6&lSandFarmer")).build();
        CraftingUtil.k = new ItemBuilder(Material.SEA_LANTERN, 4).setTitle(ChatUtil.fixColor("&6&lKopaczFosy")).build();
        CraftingUtil.b1 = new ItemBuilder(Material.BEACON).setTitle(ChatUtil.fixColor("&6&lBeacon")).build();
        CraftingUtil.back = new ItemBuilder(Material.BARRIER).setTitle(ChatUtil.fixColor("&7Wroc do menu")).build();
    }
    
    public static void openMenu(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 36, CraftingUtil.invName);
        final ItemBuilder air = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&0#"));
        final ItemBuilder air2 = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&0#"));
        inv.setItem(0, air.build());
        inv.setItem(1, air.build());
        inv.setItem(2, air.build());
        inv.setItem(3, air.build());
        inv.setItem(4, air.build());
        inv.setItem(5, air.build());
        inv.setItem(6, air.build());
        inv.setItem(7, air.build());
        inv.setItem(8, air.build());
        inv.setItem(9, air.build()); 
        inv.setItem(10, air.build()); 
        inv.setItem(11, CraftingUtil.k);
        inv.setItem(12, CraftingUtil.c);
        inv.setItem(13, CraftingUtil.f);
        inv.setItem(14, CraftingUtil.a);
        inv.setItem(15, CraftingUtil.b);
        inv.setItem(16, air.build());
        inv.setItem(17, air.build());
        inv.setItem(18, air.build());
        inv.setItem(19, air.build());
        inv.setItem(20, air.build());
        inv.setItem(21, air.build());
        inv.setItem(22, CraftingUtil.b1);
        inv.setItem(23, air.build());
        inv.setItem(24, air.build());
        inv.setItem(25, air.build());
        inv.setItem(26, air.build());
        inv.setItem(27, air.build());
        inv.setItem(28, air.build());
        inv.setItem(29, air.build());
        inv.setItem(30, air.build());
        inv.setItem(31, air.build());
        inv.setItem(32, air.build());
        inv.setItem(33, air.build());
        inv.setItem(34, air.build());
        inv.setItem(35, air.build());
        p.openInventory(inv);
    }
    
    public static void openEndStone(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName4);
        final ItemStack obs = new ItemStack(Material.COBBLESTONE);
        final ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        final ItemStack red = new ItemStack(Material.REDSTONE);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, red);
        inv.setItem(22, pearl);
        inv.setItem(23, red);
        inv.setItem(30, obs);
        inv.setItem(31, obs);
        inv.setItem(32, obs);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.a);
        p.openInventory(inv);
    }
    
    public static void openEnderchest(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName5);
        final ItemStack obs = new ItemStack(Material.OBSIDIAN);
        final ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, obs);
        inv.setItem(22, pearl);
        inv.setItem(23, obs);
        inv.setItem(30, obs);
        inv.setItem(31, obs);
        inv.setItem(32, obs);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.b);
        p.openInventory(inv);
    }
    
    public static void openBoy(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName2);
        final ItemStack obs = new ItemStack(Material.OBSIDIAN);
        final ItemStack block = new ItemStack(Material.LAVA_BUCKET);
        final ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, gold);
        inv.setItem(22, block);
        inv.setItem(23, gold);
        inv.setItem(30, obs);
        inv.setItem(31, obs);
        inv.setItem(32, obs);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.c);
        p.openInventory(inv);
    }
    
    public static void openSand(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName3);
        final ItemStack obs = new ItemStack(Material.OBSIDIAN);
        final ItemStack block = new ItemStack(Material.SAND);
        final ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, gold);
        inv.setItem(22, block);
        inv.setItem(23, gold);
        inv.setItem(30, obs);
        inv.setItem(31, obs);
        inv.setItem(32, obs);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.f);
        p.openInventory(inv);
    }

    public static void openKop(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName1);
        final ItemStack obs = new ItemStack(Material.OBSIDIAN);
        final ItemStack block = new ItemStack(Material.DIAMOND_BLOCK);
        final ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, gold);
        inv.setItem(22, block);
        inv.setItem(23, gold);
        inv.setItem(30, obs);
        inv.setItem(31, obs);
        inv.setItem(32, obs);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.k);
        p.openInventory(inv);
    }
    
    public static void openBea(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, CraftingUtil.invName6);
        final ItemStack obs = new ItemStack(Material.GLASS);
        final ItemStack block = new ItemStack(Material.WITHER_SKELETON_SKULL, 1);
        final ItemStack glas = new ItemStack(Material.OBSIDIAN);
        final ItemStack autoc = new ItemBuilder(Material.CRAFTING_TABLE).setTitle(ChatUtil.fixColor("&4&lAutoCrafting")).addLore(ChatUtil.fixColor("&7Kliknij aby automatycznie wytworzyc item.")).build();
        inv.setItem(49, CraftingUtil.back);
        inv.setItem(12, obs);
        inv.setItem(13, obs);
        inv.setItem(14, obs);
        inv.setItem(21, block);
        inv.setItem(22, block);
        inv.setItem(23, block);
        inv.setItem(30, glas);
        inv.setItem(31, glas);
        inv.setItem(32, glas);
        inv.setItem(19, autoc);
        inv.setItem(25, CraftingUtil.b1);
        p.openInventory(inv);
    }

    public static void registerRecipe() {
        final ItemStack eBoat = new ItemBuilder(Material.END_STONE, 1).setTitle(ChatUtil.fixColor("&a&lStoniarka")).addEnchantment(Enchantment.DURABILITY, 10).build();
        final NamespacedKey eBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "stone_key");
        final ShapedRecipe eBoatRecipe = new ShapedRecipe(eBoatKey, eBoat);
        eBoatRecipe.shape(new String[] { "aaa", "cbc", "aaa" });
        eBoatRecipe.setIngredient('a', Material.COBBLESTONE);
        eBoatRecipe.setIngredient('c', Material.PISTON);
        eBoatRecipe.setIngredient('b', Material.REDSTONE);
        Bukkit.addRecipe((Recipe)eBoatRecipe);

        final ItemStack eeBoat = new ItemBuilder(Material.DARK_PRISMARINE, 4).setTitle(ChatUtil.fixColor("&a&lBoyFarmer")).addEnchantment(Enchantment.DURABILITY, 10).build();
        final NamespacedKey eeBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "boy_key");
        final ShapedRecipe eeBoatRecipe = new ShapedRecipe(eeBoatKey, eeBoat);
        eeBoatRecipe.shape(new String[] { "aaa", "csc", "aaa" });
        eeBoatRecipe.setIngredient('a', Material.OBSIDIAN);
        eeBoatRecipe.setIngredient('c', Material.GOLD_BLOCK);
        eeBoatRecipe.setIngredient('s', Material.LAVA_BUCKET);
        Bukkit.addRecipe((Recipe)eeBoatRecipe);

        final ItemStack eeaBoat = new ItemBuilder(Material.PRISMARINE, 4).setTitle(ChatUtil.fixColor("&a&lSandFarmer")).addEnchantment(Enchantment.DURABILITY, 10).build();
        final NamespacedKey eeaBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "sand_key");
        final ShapedRecipe eeaBoatRecipe = new ShapedRecipe(eeaBoatKey, eeaBoat);
        eeaBoatRecipe.shape(new String[] { "aaa", "csc", "aaa" });
        eeaBoatRecipe.setIngredient('a', Material.OBSIDIAN);
        eeaBoatRecipe.setIngredient('c', Material.GOLD_BLOCK);
        eeaBoatRecipe.setIngredient('s', Material.SAND);
        Bukkit.addRecipe((Recipe)eeaBoatRecipe);

        final ItemStack eeaeBoat = new ItemBuilder(Material.SEA_LANTERN, 4).setTitle(ChatUtil.fixColor("&a&lKopaczFosy")).addEnchantment(Enchantment.DURABILITY, 10).build();
        final NamespacedKey eeaeBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "kop_key");
        final ShapedRecipe eeaeBoatRecipe = new ShapedRecipe(eeaeBoatKey, eeaeBoat);
        eeaeBoatRecipe.shape(new String[] { "aaa", "csc", "aaa" });
        eeaeBoatRecipe.setIngredient('a', Material.OBSIDIAN);
        eeaeBoatRecipe.setIngredient('c', Material.GOLD_BLOCK);
        eeaeBoatRecipe.setIngredient('s', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe((Recipe)eeaeBoatRecipe);

        final ItemStack eeaeaBoat = new ItemBuilder(Material.ENDER_CHEST, 1).build();
        final NamespacedKey eeaeaBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "ec_key");
        final ShapedRecipe eeaeaBoatRecipe = new ShapedRecipe(eeaeaBoatKey, eeaeaBoat);
        eeaeaBoatRecipe.shape(new String[] { "sss", "sps", "sss" });
        eeaeaBoatRecipe.setIngredient('s', Material.OBSIDIAN);
        eeaeaBoatRecipe.setIngredient('p', Material.GOLD_BLOCK);
        Bukkit.addRecipe((Recipe)eeaeaBoatRecipe);

        final ItemStack eeaeaaBoat = new ItemBuilder(Material.BEACON, 1).build();
        final NamespacedKey eeaeaaBoatKey = new NamespacedKey(ServerPlugin.getPlugin(), "bc_key");
        final ShapedRecipe eeaeaaBoatRecipe = new ShapedRecipe(eeaeaaBoatKey, eeaeaaBoat);
        eeaeaaBoatRecipe.shape(new String[] { "ggg", "sss", "ooo" });
        eeaeaaBoatRecipe.setIngredient('g', Material.GLASS);
        eeaeaaBoatRecipe.setIngredient('s', Material.WITHER_SKELETON_SKULL);
        eeaeaaBoatRecipe.setIngredient('o', Material.OBSIDIAN);
        Bukkit.addRecipe((Recipe)eeaeaaBoatRecipe);
    }

}
