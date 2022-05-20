// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.packets;

import pl.virtual.api.utils.Reflection;

public enum PacketType
{
    PLAY_OUT_SCOREBOARD_TEAM("PLAY_OUT_SCOREBOARD_TEAM", 0, "PacketPlayOutScoreboardTeam"), 
    PLAY_OUT_PLAYER_INFO("PLAY_OUT_PLAYER_INFO", 1, "PacketPlayOutPlayerInfo"), 
    PLAY_OUT_WORLD_PARTICLES("PLAY_OUT_WORLD_PARTICLES", 2, "PacketPlayOutWorldParticles"), 
    PLAY_OUT_BLOCK_BREAK_ANIMATION("PLAY_OUT_BLOCK_BREAK_ANIMATION", 3, "PacketPlayOutBlockBreakAnimation");
    
    private String className;
    
    private PacketType(final String s, final int n, final String name) {
        this.className = name;
    }
    
    public Class<?> getPacket() {
        return Reflection.getMinecraftClass(this.className);
    }
    
    public String getClassName() {
        return this.className;
    }
}
