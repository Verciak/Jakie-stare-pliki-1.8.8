// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class WeatherTask extends BukkitRunnable
{
    @Override
    public void run() {
        for (final World world : Bukkit.getWorlds()) {
            world.setStorm(false);
        }
    }
}
