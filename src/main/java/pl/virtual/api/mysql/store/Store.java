// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.mysql.store;

import java.sql.ResultSet;

import pl.virtual.api.mysql.store.modes.StoreMode;

import java.sql.Connection;

public interface Store
{
    Connection getConnection();
    
    boolean connect();
    
    void disconnect();
    
    void reconnect();
    
    boolean isConnected();
    
    ResultSet query(final String p0);
    
    void query(final String p0, final Callback<ResultSet> p1);
    
    void update(final boolean p0, final String p1);
    
    ResultSet update(final String p0);
    
    StoreMode getStoreMode();
}
