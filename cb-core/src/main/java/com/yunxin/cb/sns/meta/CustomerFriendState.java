package com.yunxin.cb.sns.meta;

public enum CustomerFriendState {
    NORMAL(1),//正常
    BLACKLIST(2)//黑名单
    ;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state;
   private CustomerFriendState(int state){
       this.state=state;
   }

    @Override
    public String toString() {
        return "CustomerFriendState{" +
                "state=" + state +
                '}';
    }
}
