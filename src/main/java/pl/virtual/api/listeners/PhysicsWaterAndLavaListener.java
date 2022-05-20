// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.block.BlockFromToEvent;

import org.bukkit.event.Listener;

public class PhysicsWaterAndLavaListener implements Listener
{
    @EventHandler
    public void Rozlanie(final BlockFromToEvent e) {
        final Block m = e.getBlock();
        if (m.getLocation().getY() > 50.0 && (m.getType() == Material.WATER || m.getType() == Material.LEGACY_STATIONARY_WATER || m.getType() == Material.LAVA || m.getType() == Material.LEGACY_STATIONARY_LAVA)) {
            e.setCancelled(true);
        }
    }
}
