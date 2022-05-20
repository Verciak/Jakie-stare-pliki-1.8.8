// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class HelpCommand extends PlayerCommand
{
    public HelpCommand() {
        super("help", "komenda do oi", "/help", "core.cmd.user", new String[] { "pomoc" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        sender.sendMessage(ChatUtil.fixColor("&8&m--------- &8[ &7POMOC &8]&8&m---------"));
        sender.sendMessage(ChatUtil.fixColor(""));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/vip &8- &7Informacje dotyczace rangi VIP"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/svip &8- &7Informacje dotyczace rangi SVIP"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/sponsor &8- &7Informacje dotyczace rangi SPONSOR"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/ignore <nick> &8- &7Ignorowanie msg/tpa od graczy"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/wiadomosci &8- &7Konfiguracja wiadomosci"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/statystyki &8- &7Inne statystyki"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/efekty &8- &7efekty serwerowe"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/yt &8- &7Informacje dotyczace rangi YouTube"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/gildie &8- &7Komendy gildii"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/helpop &8- &7Wiadomosc do administracji"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/crafting &8- &7Informacje o craftingach"));
        sender.sendMessage(ChatUtil.fixColor("&8» &9/znaki &8- &7Pokazuje znaki dostepne na chacie"));
        sender.sendMessage(ChatUtil.fixColor(""));
        sender.sendMessage(ChatUtil.fixColor("&8&m--------- &8[ &7POMOC &8]&8&m---------"));
        return false;
    }
}
