// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import org.bukkit.entity.Player;

import pl.virtual.api.data.base.drops.CancelDropData;
import pl.virtual.api.data.base.drops.Drop;
import pl.virtual.api.data.base.drops.DropData;
import pl.virtual.api.data.base.drops.NormalDropData;
import pl.virtual.api.data.base.drops.RandomDropData;

import java.util.Iterator;

import org.bukkit.Material;
import java.util.HashMap;

public class DropManager
{
    private static final HashMap<Material, DropData> drops;
    private static final HashMap<Material, Integer> exps;
    
    static {
        drops = new HashMap<Material, DropData>();
        exps = new HashMap<Material, Integer>();
    }
    
    public static void setup() {
        DropManager.drops.clear();
        DropManager.exps.clear();
        for (final String s : DropFile.getConfig().getStringList("cancel-drops")) {
            DropManager.drops.put(Material.getMaterial(s), new CancelDropData());
        }
        final RandomDropData data = new RandomDropData();
        for (final Drop d : RandomDropData.getDrops()) {
            DropManager.drops.put(d.getFrom(), data);
        }
        for (final String s2 : DropFile.getConfig().getConfigurationSection("exp-drops").getKeys(false)) {
            DropManager.exps.put(Material.getMaterial(s2), DropFile.getConfig().getInt("exp-drops." + s2, 1));
        }
    }
    
    public static DropData getDropData(final Material mat) {
        DropData drop = new NormalDropData();
        if (DropManager.drops.containsKey(mat)) {
            drop = DropManager.drops.get(mat);
        }
        return drop;
    }
    
    public static int getExp(final Material mat, final Player p) {
        int exp = 0;
        if (DropManager.exps.containsKey(mat)) {
            exp = DropManager.exps.get(mat);
        }
        return exp;
    }
    
    public static HashMap<Material, DropData> getDrops() {
        return DropManager.drops;
    }
    
    public static HashMap<Material, Integer> getExps() {
        return DropManager.exps;
    }
}
