// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemStackUtil;
import pl.virtual.api.utils.ItemUtil;

import org.bukkit.command.CommandSender;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class LimitTask extends BukkitRunnable
{
	
    public static boolean isInSpawn(final Location loc) {
        return loc.getBlockX() <= Config.REGION_SPAWN && loc.getBlockX() >= -Config.REGION_SPAWN - 1 && loc.getBlockZ() <= Config.REGION_SPAWN && loc.getBlockZ() >= -Config.REGION_SPAWN - 1;
    }
	
	@Override
	public void run() {
	    for (final Player p : Bukkit.getOnlinePlayers()) {
	        final User u = UserManager.getUser(p);
	        if (!isInSpawn(p.getLocation()) && u != null) {
	            final int koxAmount = ItemStackUtil.getAmountOfItem(Material.ENCHANTED_GOLDEN_APPLE, p, (short) 0);
	            final int refAmount = ItemStackUtil.getAmountOfItem(Material.GOLDEN_APPLE, p, (short)0);
	            final int pearlAmount = ItemStackUtil.getAmountOfItem(Material.ENDER_PEARL, p, (short)0);
	            final int snowAmount = ItemStackUtil.getAmountOfItem(Material.SNOWBALL, p, (short)0);
	            final int arrowAmount = ItemStackUtil.getAmountOfItem(Material.ARROW, p, (short)0);
	            if (koxAmount > Config.LIMIT_KOX) {
	                final int kf = koxAmount - Config.LIMIT_KOX;
	                ItemUtil.removeItemsDepo(Material.ENCHANTED_GOLDEN_APPLE, p, (short)0, kf);
	                u.addKox(kf);
	                u.save();
	                ChatUtil.sendMessage((CommandSender)p, "&7Nadmiar koxow zostal przeniesiony do depozytu");
	            }
	            if (refAmount > Config.LIMIT_REFILE) {
	                final int rf = refAmount - Config.LIMIT_REFILE;
	                ItemUtil.removeItemsDepo(Material.GOLDEN_APPLE, p, (short)0, rf);
	                u.addRefil(rf);
	                u.save();
	                ChatUtil.sendMessage((CommandSender)p, "&7Nadmiar refili zostal przeniesiony do depozytu");
	            }
	            if (pearlAmount > Config.LIMIT_PEARL) {
	                final int pr = pearlAmount - Config.LIMIT_PEARL;
	                ItemUtil.removeItemsDepo(Material.ENDER_PEARL, p, (short)0, pr);
	                u.addPerly(pr);
	                u.save();
	                ChatUtil.sendMessage((CommandSender)p, "&7Nadmiar perel zostal przeniesiony do depozytu");
	            }
	            if (snowAmount > Config.LIMIT_SNOW) {
	                final int sn = snowAmount - Config.LIMIT_SNOW;
	                ItemUtil.removeItemsDepo(Material.SNOWBALL, p, (short)0, sn);
	                u.addSnow(sn);
	                u.save();
	                ChatUtil.sendMessage((CommandSender)p, "&7Nadmiar sniezek zostal przeniesiony do depozytu");
	            }
	            if (arrowAmount > Config.LIMIT_ARROW) {
	                final int ar = arrowAmount - Config.LIMIT_ARROW;
	                ItemUtil.removeItemsDepo(Material.ARROW, p, (short)0, ar);
	                u.addArrow(ar);
	                u.save();
	                ChatUtil.sendMessage((CommandSender)p, "&7Nadmiar strzal zostal przeniesiony do depozytu");
	            }
	        }
	    }
	}
}
