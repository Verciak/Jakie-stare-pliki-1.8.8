// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.NameTag;

public enum NameTagMode
{
    TAG_API("TAG_API", 0, "tagapi"), 
    SCOREBOARD("SCOREBOARD", 1, "scoreboard");
    
    private String name;
    
    private NameTagMode(final String s, final int n, final String name) {
        this.name = name;
    }
    
    public static NameTagMode getByName(final String name) {
        NameTagMode[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final NameTagMode sm = values[i];
            if (sm.getName().equalsIgnoreCase(name)) {
                return sm;
            }
        }
        return null;
    }
    
    public String getName() {
        return this.name;
    }
}
