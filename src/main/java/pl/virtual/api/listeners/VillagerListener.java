package pl.virtual.api.listeners;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.gui.SklepGui;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;

public class VillagerListener implements Listener {
	
    @EventHandler
    public void PlayerRightClick(final PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        final Guild g = GuildManager.getGuild(player);
        final Entity entity = event.getRightClicked();
        if (event.getRightClicked() instanceof Villager && entity.getCustomName().equalsIgnoreCase(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lVillager &7&k:&f&k:&7&k:&6&l&l"))) {
			event.setCancelled(true);
	        if (g == null) {
	        	SklepGui.show(player);
	        	return;	
	        }else {
		        SklepGui.showguild(player);
				return;	
	        }
		}
    }
}
