// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;

import pl.virtual.api.ServerPlugin;

public final class Logger
{
    public static void info(final String... logs) {
        for (final String s : logs) {
            log(Level.INFO, s);
        }
    }
    
    public static void warning(final String... logs) {
        for (final String s : logs) {
            log(Level.WARNING, s);
        }
    }
    
    public static void severe(final String... logs) {
        for (final String s : logs) {
            log(Level.SEVERE, s);
        }
    }
    
    public static void log(final Level level, final String log) {
        ServerPlugin.getPlugin().getLogger().log(level, log);
    }
    
    public static void exception(final Throwable cause) {
        cause.printStackTrace();
    }
    
    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
