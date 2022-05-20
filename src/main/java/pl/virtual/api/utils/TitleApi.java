// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class TitleApi
{
    public static boolean sendTitle(final CommandSender player, final String text) {
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\",color:" + ChatColor.GRAY.name().toLowerCase() + "}");
        final PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        final PacketPlayOutTitle length = new PacketPlayOutTitle(10, 40, 10);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)title);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)length);
		return false;
    }
    
    public static boolean sendSubTitle(final CommandSender sender, final String text) {
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\",color:" + ChatColor.GRAY.name().toLowerCase() + "}");
        final PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatTitle);
        final PacketPlayOutTitle length = new PacketPlayOutTitle(10, 40, 10);
        ((CraftPlayer)sender).getHandle().playerConnection.sendPacket((Packet)title);
        ((CraftPlayer)sender).getHandle().playerConnection.sendPacket((Packet)length);
		return false;
    }
        
    public static String fix(final String text) {
         return text.replaceAll("&", "�").replace(">>", "�").replace("<<", "�");
  }
}
