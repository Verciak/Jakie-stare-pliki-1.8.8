// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base;

import org.bukkit.command.CommandSender;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.mysql.store.Callback;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.ItemBuilder;
import pl.virtual.api.utils.ItemSerializer;
import pl.virtual.api.utils.Logger;

import org.bukkit.inventory.Inventory;
import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;

import java.sql.ResultSet;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Backup
{
    private String name;
    private long time;
    private int ping;
    private String killer;
    private ItemStack[] inventory;
    private ItemStack[] armor;
    private ItemStack[] enderchest;
    
    public Backup(final Player player, final String killer) {
        this.name = player.getName();
        this.time = System.currentTimeMillis();
        this.killer = killer;
        this.inventory = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
        this.enderchest = player.getEnderChest().getContents();
        this.insert();
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}backups`(`id`, `name`, `time`, `killer`, `ping`, `inventory`, `armor`, `enderchest`) VALUES (NULL, '" + this.getName() + "','" + this.getTime() + "','" + this.getKiller() + "','" + this.getPing() + "','" + ItemSerializer.itemsToString(this.getInventory()) + "','" + ItemSerializer.itemsToString(this.getArmor()) + "','" + ItemSerializer.itemsToString(this.getEnderchest()) + "');");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(final long time) {
        this.time = time;
    }
    
    public int getPing() {
        return this.ping;
    }
    
    public void setPing(final int ping) {
        this.ping = ping;
    }
    
    public String getKiller() {
        return this.killer;
    }
    
    public void setKiller(final String killer) {
        this.killer = killer;
    }
    
    public ItemStack[] getInventory() {
        return this.inventory;
    }
    
    public void setInventory(final ItemStack[] inventory) {
        this.inventory = inventory;
    }
    
    public ItemStack[] getArmor() {
        return this.armor;
    }
    
    public void setArmor(final ItemStack[] armor) {
        this.armor = armor;
    }
    
    public ItemStack[] getEnderchest() {
        return this.enderchest;
    }
    
    public void setEnderchest(final ItemStack[] enderchest) {
        this.enderchest = enderchest;
    }
    
    public static void getList(final Player p, final Player o) {
        ServerPlugin.getStore().query("SELECT * FROM `{P}backups` WHERE name ='" + p.getName() + "' ORDER BY `id` DESC LIMIT 27;", new Callback<ResultSet>() {
            @Override
            public ResultSet done(final ResultSet rs) {
                try {
                    final Inventory inventory = Bukkit.createInventory((InventoryHolder)p, 27, ChatUtil.fixColor("&4&lBackup'y gracza"));
                    while (rs.next()) {
                        final String player = rs.getString("name");
                        final long time = rs.getLong("time");
                        final int ping = rs.getInt("ping");
                        final String killer = rs.getString("killer");
                        final ItemBuilder i = new ItemBuilder(Material.WRITTEN_BOOK).setTitle(ChatUtil.fixColor("ID: " + time));
                        i.addLore(ChatUtil.fixColor("&7Gracz: &6" + player));
                        i.addLore(ChatUtil.fixColor("&7Ping: &6" + ping));
                        i.addLore(ChatUtil.fixColor("&7Killer: &6" + killer));
                        i.addLore(ChatUtil.fixColor("&7Data: &6" + DataUtil.getDate(time)));
                        inventory.addItem(new ItemStack[] { i.build() });
                    }
                    o.openInventory(inventory);
                    rs.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
            
            @Override
            public void error(final Throwable p0) {
                Logger.warning("Error when get `backup` ", p0.getMessage());
            }
        });
    }
    
    public static void restore(final Player p, final long time, final int mode, final Player o) {
        ServerPlugin.getStore().query("SELECT *, abs(" + time + " - time) as delta FROM `{P}backups` WHERE `name` = '" + p.getName() + "' ORDER BY delta LIMIT 1", new Callback<ResultSet>() {
            @Override
            public ResultSet done(final ResultSet rs) {
                try {
                    if (rs.next()) {
                        switch (mode) {
                            case 0: {
                                p.getInventory().setArmorContents(ItemSerializer.stringToItems(rs.getString("armor")));
                                p.getInventory().setContents(ItemSerializer.stringToItems(rs.getString("inventory")));
                                ChatUtil.sendMessage((CommandSender)p, "&7Twoj ekwipunek zostal cofniety do &c" + DataUtil.getDate(time) + " &7przez &c" + o.getName());
                                ChatUtil.sendMessage((CommandSender)o, "&7Cofneles ekwipunek do &c" + DataUtil.getDate(time) + " &7graczowi &c" + p.getName());
                                break;
                            }
                            case 1: {
                                p.getInventory().setArmorContents(ItemSerializer.stringToItems(rs.getString("armor")));
                                p.getInventory().setContents(ItemSerializer.stringToItems(rs.getString("inventory")));
                                p.getEnderChest().setContents(ItemSerializer.stringToItems(rs.getString("enderchest")));
                                ChatUtil.sendMessage((CommandSender)p, "&7Twoj ekwipunek + enderchest zostal cofniety do &c" + DataUtil.getDate(time) + " &7przez &c" + o.getName());
                                ChatUtil.sendMessage((CommandSender)o, "&7Cofneles ekwipunek + enderchest do &c" + DataUtil.getDate(time) + " &7graczowi &c" + p.getName());
                                break;
                            }
                            case 2: {
                                p.getEnderChest().setContents(ItemSerializer.stringToItems(rs.getString("enderchest")));
                                ChatUtil.sendMessage((CommandSender)p, "&7Twoj enderchest zostal cofniety do &c" + DataUtil.getDate(time) + " &7przez &c" + o.getName());
                                ChatUtil.sendMessage((CommandSender)o, "&7Cofneles enderchest do &c" + DataUtil.getDate(time) + " &7graczowi &c" + p.getName());
                                break;
                            }
                        }
                    }
                    return null;
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            
            @Override
            public void error(final Throwable p0) {
                Logger.warning("Error when restore `backup` ", p0.getMessage());
            }
        });
    }
}
