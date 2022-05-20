// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.Listener;

public class SignChangeListener implements Listener
{
    @EventHandler
    public void sing(final SignChangeEvent e) {
        if (!e.getPlayer().hasPermission("core.cmd.admin")) {
            return;
        }
        for (int i = 0; i <= 3; ++i) {
            e.setLine(i, ChatUtil.fixColor(e.getLine(i)));
        }
    }
}
