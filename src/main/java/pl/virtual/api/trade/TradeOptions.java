package pl.virtual.api.trade;

import pl.virtual.api.data.base.user.User;

public class TradeOptions
{
    private boolean isTrading;
    private User lastOffer;
    private Trade trade;
    private boolean ready;
    
    public TradeOptions(final User user) {
        this.ready = false;
    }
    
    public boolean isTrading() {
        return this.isTrading;
    }
    
    public void setIsTrading(final boolean isTrading) {
        this.isTrading = isTrading;
    }
    
    public User getLastOffer() {
        return this.lastOffer;
    }
    
    public void setLastOffer(final User lastOffer) {
        this.lastOffer = lastOffer;
    }
    
    public Trade getTrade() {
        return this.trade;
    }
    
    public void setTrade(final Trade trade) {
        this.trade = trade;
    }
    
    public boolean isReady() {
        return this.ready;
    }
    
    public void setReady(final boolean ready) {
        this.ready = ready;
    }
}

