// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.Material;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class TakeCrystalListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerInteract(final PlayerInteractEvent event) {
        final Block b = event.getClickedBlock();
        if (event.isCancelled()) {
            return;
        }
        if (b == null) {
            return;
        }
        final Guild g = GuildManager.getGuild(b.getLocation());
        if (b.getType() != Material.SPONGE) {
            return;
        }
        if (g == null) {
            return;
        }
    }
}
