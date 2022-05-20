// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import java.math.RoundingMode;
import org.bukkit.plugin.Plugin;

import pl.virtual.api.ServerPlugin;

import org.bukkit.Bukkit;
import java.util.LinkedList;
import java.text.DecimalFormat;

public class Ticking implements Runnable
{
    private static DecimalFormat df;
    private transient long lastPoll;
    private final LinkedList<Double> history;
    private static String result;
    
    static {
        Ticking.df = new DecimalFormat("#,###.##");
        Ticking.result = "20.0";
    }
    
    public Ticking() {
        this.lastPoll = System.nanoTime();
        (this.history = new LinkedList<Double>()).add(20.0);
    }
    
    public void start() {
        Bukkit.getScheduler().runTaskTimer((Plugin)ServerPlugin.getPlugin(), (Runnable)this, 1000L, 50L);
    }
    
    @Override
    public void run() {
        final long startTime = System.nanoTime();
        long timeSpent = (startTime - this.lastPoll) / 1000L;
        if (timeSpent == 0L) {
            timeSpent = 1L;
        }
        if (this.history.size() > 10) {
            this.history.remove();
        }
        final double tps = 5.0E7 / timeSpent;
        if (tps <= 21.0) {
            this.history.add(tps);
        }
        this.lastPoll = startTime;
        double avg = 0.0;
        for (final Double f : this.history) {
            if (f != null) {
                avg += f;
            }
        }
        Ticking.df.setRoundingMode(RoundingMode.HALF_UP);
        Ticking.result = Ticking.df.format(avg / this.history.size());
    }
    
    public static String getTPS() {
        return Ticking.result;
    }
}
