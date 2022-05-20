// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.drops;

import java.util.Arrays;

import pl.virtual.api.managers.DropFile;
import pl.virtual.api.utils.ChatUtil;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Set;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import java.util.List;

public class Drop
{
    private final String name;
    private final double chance;
    private final int exp;
    private final String message;
    private final boolean fortune;
    private final List<Biome> biomes;
    private final List<Material> tools;
    private final int minHeight;
    private final int maxHeight;
    private final int minAmount;
    private final int maxAmount;
    private final ItemStack what;
    private final Material from;
    private final Set<UUID> disabled;
    
    public Drop(final String name) {
        this.biomes = new ArrayList<Biome>();
        this.tools = new ArrayList<Material>();
        this.disabled = new HashSet<UUID>();
        this.name = name;
        this.chance = DropFile.getConfig().getDouble("random-drops." + name + ".chance");
        this.exp = DropFile.getConfig().getInt("random-drops." + name + ".exp");
        this.message = DropFile.getConfig().getString("random-drops." + name + ".message");
        this.fortune = DropFile.getConfig().getBoolean("random-drops." + name + ".fortune");
        this.minHeight = DropFile.getConfig().getInt("random-drops." + name + ".height.min");
        this.maxHeight = DropFile.getConfig().getInt("random-drops." + name + ".height.max");
        this.minAmount = DropFile.getConfig().getInt("random-drops." + name + ".amount.min");
        this.maxAmount = DropFile.getConfig().getInt("random-drops." + name + ".amount.max");
        ItemStack what = ChatUtil.getItemStackFromString(DropFile.getConfig().getString("random-drops." + name + ".drop.what"));
        if (what.getType() == Material.CHEST) {
        }
        this.what = what;
        this.from = Material.getMaterial(DropFile.getConfig().getString("random-drops." + name + ".drop.from"));
        for (final String s : DropFile.getConfig().getStringList("random-drops." + name + ".biome")) {
            this.biomes.add(Biome.valueOf(s));
        }
        for (final String s : DropFile.getConfig().getStringList("random-drops." + name + ".tool")) {
            this.tools.add(Material.getMaterial(s));
        }
        if (this.biomes.size() == 0) {
            this.biomes.addAll(Arrays.asList(Biome.values()));
        }
        if (this.tools.size() == 0) {
            this.tools.addAll(Arrays.asList(Material.values()));
        }
    }
    
    public void changeStatus(final UUID uuid) {
        if (this.disabled.contains(uuid)) {
            this.disabled.remove(uuid);
        }
        else {
            this.disabled.add(uuid);
        }
    }
    
    public void setStatus(final UUID uuid, final boolean b) {
        if (b) {
            if (this.disabled.contains(uuid)) {
                this.disabled.remove(uuid);
            }
        }
        else if (!this.disabled.contains(uuid)) {
            this.disabled.add(uuid);
        }
    }
    
    public boolean isDisabled(final UUID uuid) {
        return this.disabled.contains(uuid);
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getChance() {
        return this.chance;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public boolean isFortune() {
        return this.fortune;
    }
    
    public List<Biome> getBiomes() {
        return this.biomes;
    }
    
    public List<Material> getTools() {
        return this.tools;
    }
    
    public int getMinHeight() {
        return this.minHeight;
    }
    
    public int getMaxHeight() {
        return this.maxHeight;
    }
    
    public int getMinAmount() {
        return this.minAmount;
    }
    
    public int getMaxAmount() {
        return this.maxAmount;
    }
    
    public ItemStack getWhat() {
        return this.what;
    }
    
    public Material getFrom() {
        return this.from;
    }
    
    public Set<UUID> getDisabled() {
        return this.disabled;
    }
}
