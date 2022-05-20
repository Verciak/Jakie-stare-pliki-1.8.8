// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import org.bukkit.entity.Player;

import pl.virtual.api.data.base.Combat;

import java.util.concurrent.ConcurrentHashMap;

public class CombatManager
{
    private static final ConcurrentHashMap<Player, Combat> combats;
    
    static {
        combats = new ConcurrentHashMap<Player, Combat>();
    }
    
    public static Combat getCombat(final Player p) {
        return CombatManager.combats.get(p);
    }
    
    public static void createCombat(final Player p) {
        final Combat combat = new Combat(p);
        CombatManager.combats.put(p, combat);
    }
    
    public static void removeCombat(final Player p) {
        CombatManager.combats.remove(p);
    }
    
    public static ConcurrentHashMap<Player, Combat> getCombats() {
        return CombatManager.combats;
    }
}
