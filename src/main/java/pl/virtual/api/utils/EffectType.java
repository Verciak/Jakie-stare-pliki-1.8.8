// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum EffectType
{
    REGEN("REGEN", 0, "REGEN", 0, "REGEN", 0, "REGEN", 0, 20, 150), 
    INSTANT_HEAL("INSTANT_HEAL", 1, "INSTANT_HEAL", 1, "INSTANT_HEAL", 1, "INSTANT_HEAL", 1, true, 240, -1), 
    SPEED("SPEED", 2, "SPEED", 2, "SPEED", 2, "SPEED", 2, 18, 90), 
    FIRE_RESISTANCE("FIRE_RESISTANCE", 3, "FIRE_RESISTANCE", 3, "FIRE_RESISTANCE", 3, "FIRE_RESISTANCE", 3, 16, 512), 
    HASTE("HASTE", 4, "HASTE", 4, "HASTE", 4, "HASTE", 4, 32, 300), 
    INSTANT_DAMAGE("INSTANT_DAMAGE", 5, "INSTANT_DAMAGE", 5, "INSTANT_DAMAGE", 5, "INSTANT_DAMAGE", 5, true, 20, -1);
    
    private final boolean isInstant;
    private final int cost;
    private final int duration;
    
    private EffectType(final String s4, final int n4, final String s3, final int n3, final String s2, final int n2, final String s, final int n, final boolean isInstant, final int cost, final int duration) {
        this.cost = cost;
        this.isInstant = isInstant;
        this.duration = duration;
    }
    
    private EffectType(final String s4, final int n4, final String s3, final int n3, final String s2, final int n2, final String s, final int n, final int cost, final int duration) {
        this.cost = cost;
        this.isInstant = false;
        this.duration = duration;
    }
    
    public boolean isInstant() {
        return this.isInstant;
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public static EffectType getEffectTypeRandom() {
        final int random = RandomUtil.getRandInt(1, 4);
        if (random == 1) {
            return EffectType.FIRE_RESISTANCE;
        }
        if (random == 2) {
            return EffectType.HASTE;
        }
        if (random == 3) {
            return EffectType.REGEN;
        }
        if (random == 4) {
            return EffectType.SPEED;
        }
        return null;
    }
    
    public static void applyPotion(final Player player, final EffectType type, final long timeTo) {
        System.out.println("Time to:" + timeTo + " Time now:" + System.currentTimeMillis());
        final long timeLeft = timeTo - System.currentTimeMillis();
        Math.round(timeLeft / 1000L);
        if (type == EffectType.FIRE_RESISTANCE) {
            player.addPotionEffect(new PotionEffect(PotionType.FIRE_RESISTANCE.getEffectType(), 10240, 1), true);
        }
        else if (type == EffectType.HASTE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1), true);
        }
        else if (type == EffectType.INSTANT_DAMAGE) {
            player.addPotionEffect(new PotionEffect(PotionType.INSTANT_DAMAGE.getEffectType(), 0, 2));
        }
        else if (type == EffectType.INSTANT_HEAL) {
            player.addPotionEffect(new PotionEffect(PotionType.INSTANT_HEAL.getEffectType(), 0, 2));
        }
        else if (type == EffectType.REGEN) {
            player.addPotionEffect(new PotionEffect(PotionType.REGEN.getEffectType(), 3000, 1), true);
        }
        else if (type == EffectType.SPEED) {
            player.addPotionEffect(new PotionEffect(PotionType.SPEED.getEffectType(), 1800, 1), true);
        }
    }
}
