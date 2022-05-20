// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.entity.Player;

import pl.virtual.api.data.base.user.User;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.Sound;
import org.bukkit.Effect;

public class LevelUtil
{
    public static void checkLevel(final User u) {
        final int lvl = u.getLvl();
        if (lvl > 100) {
            return;
        }
        final int wzor = u.getLvl() * 100 * u.getLvl();
        final int exp = u.getExp();
        if (exp >= wzor) {
            u.setLvl(u.getLvl() + 1);
            u.save();
            final Player p = u.getPlayer();
            p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0f, 25.0f);
            if (u.getLvl() >= 10 && u.getLvl() % 10 == 0) {
                u.addCoins(100);
                u.save();
            }
        }
    }
}
