// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.mysql.store;

public interface Callback<T>
{
    T done(final T p0);
    
    void error(final Throwable p0);
}
