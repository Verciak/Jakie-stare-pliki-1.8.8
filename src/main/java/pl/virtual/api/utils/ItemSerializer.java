// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.meta.ItemMeta;

import pl.virtual.api.utils.Logger;

import java.util.HashMap;
import org.bukkit.Material;
import java.util.Map;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import javax.xml.bind.DatatypeConverter;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;

public class ItemSerializer
{
    public static String itemsToString(final ItemStack[] items) {
        try {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(serializeItemStack(items));
            oos.flush();
            return DatatypeConverter.printBase64Binary(bos.toByteArray());
        }
        catch (Exception e) {
            Logger.exception(e);
            return "";
        }
    }
    
    public static ItemStack[] stringToItems(final String s) {
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(s));
            final ObjectInputStream ois = new ObjectInputStream(bis);
            return deserializeItemStack((Map<String, Object>[])ois.readObject());
        }
        catch (Exception e) {
            Logger.exception(e);
            return new ItemStack[] { new ItemStack(Material.AIR) };
        }
    }
    
    private static Map<String, Object>[] serializeItemStack(final ItemStack[] items) {
        final Map[] result = new Map[items.length];
        for (int i = 0; i < items.length; ++i) {
            final ItemStack is = items[i];
            if (is == null) {
                result[i] = new HashMap();
            }
            else {
                result[i] = is.serialize();
                if (is.hasItemMeta()) {
                    result[i].put("meta", is.getItemMeta().serialize());
                }
            }
        }
        return (Map<String, Object>[])result;
    }
    
    private static ItemStack[] deserializeItemStack(final Map<String, Object>[] map) 
    {
        final ItemStack[] items = new ItemStack[map.length];
        for (int i = 0; i < items.length; ++i) {
            final Map<String, Object> s = map[i];
            if (s.size() == 0) {
                items[i] = null;
            }
            else {
                try {
                    if (s.containsKey("meta")) {
                        final Map<String, Object> im = new HashMap<String, Object>((Map<? extends String, ?>) s.remove("meta"));
                        im.put("==", "ItemMeta");
                        final ItemStack is = ItemStack.deserialize((Map<String, Object>)s);
                        is.setItemMeta((ItemMeta)ConfigurationSerialization.deserializeObject((Map<String, Object>)im));
                        items[i] = is;
                    }
                    else {
                        items[i] = ItemStack.deserialize((Map<String, Object>)s);
                    }
                }
                catch (Exception e) {
                    Logger.exception(e);
                    items[i] = null;
                }
            }
        }
        return items;
    }
}
