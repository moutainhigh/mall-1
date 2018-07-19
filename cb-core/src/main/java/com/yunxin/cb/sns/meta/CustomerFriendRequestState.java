package com.yunxin.cb.sns.meta;

public enum CustomerFriendRequestState {
    NEWREQUEST(0),//新请求
    AGREE(1),//同意
    REFUSE(2),//同意
    IGNORE(3)//同意
    ;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state;
   private CustomerFriendRequestState(int state){
       this.state=state;
   }

    @Override
    public String toString() {
        return "CustomerFriendState{" +
                "state=" + state +
                '}';
    }
}
