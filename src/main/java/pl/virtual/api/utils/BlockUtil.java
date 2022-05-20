// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

public final class BlockUtil
{
    private static Reflection.FieldAccessor<Float> durabilityField;
    private static Reflection.FieldAccessor<Float> strengthField;
    
    static {
        BlockUtil.durabilityField = Reflection.getField(Reflection.getMinecraftClass("Block"), "durability", Float.TYPE);
        BlockUtil.strengthField = Reflection.getField(Reflection.getMinecraftClass("Block"), "strength", Float.TYPE);
    }
    
    public static void setDurability(final String name, final float durability) {
        final Reflection.FieldAccessor<Object> f = Reflection.getSimpleField(Reflection.getMinecraftClass("Blocks"), name.toUpperCase());
        BlockUtil.durabilityField.set(f.get(null), durability);
        Logger.info("Wyrzymalosc " + name + " wynosi " + durability);
    }
    
    public static void setStrength(final String name, final float strength) {
        final Reflection.FieldAccessor<Object> f = Reflection.getSimpleField(Reflection.getMinecraftClass("Blocks"), name.toUpperCase());
        BlockUtil.strengthField.set(f.get(null), strength);
        Logger.info("Strength of " + name + " was changed to " + strength);
    }
}
