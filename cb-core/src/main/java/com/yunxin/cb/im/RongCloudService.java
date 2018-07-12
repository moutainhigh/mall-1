package com.yunxin.cb.im;

import com.yunxin.cb.mall.entity.Customer;
import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.methods.message.Message;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.message.SystemMessage;
import io.rong.models.response.BlackListResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RongCloudService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${rongcloud.appKey}")
    private String appKey = "appKey";

    @Value("${rongcloud.appSecret}")
    private String appSecret = "appSecret";

    public String register(Customer customer) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;
        UserModel user = new UserModel()
                .setId(customer.getAccountName())
                .setName(customer.getAccountName())
                .setPortrait(customer.getAvatarUrl());

        TokenResult result = User.register(user);
        if(result.getCode() == 200){
            logger.info("getRongCloudToken:  " + result.toString());
            return result.getToken();
        }else {
            throw new Exception(result.getMsg());
        }
    }

    public void update(Customer customer) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;
        UserModel user = new UserModel()
                .setId(customer.getAccountName())
                .setName(customer.getNickName())
                .setPortrait(customer.getAvatarUrl());

        Result result = User.update(user);
        if(result.getCode() != 200){
            throw new Exception(result.getMsg());
        }
    }

    public void sendMessage(Customer customer,Customer friend,String requestMessage) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        String content=customer.getNickName()+"加您为好友";
        TxtMessage txtMessage = new TxtMessage(content,"");
        SystemMessage systemMessage = new SystemMessage()
                .setSenderId(customer.getMobile())
                .setTargetId(new String[]{friend.getMobile()})
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent(content)
                .setPushData("{'pushData':'"+requestMessage+"'}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);
        ResponseResult result = rongCloud.message.system.send(systemMessage);


        if(result.getCode() != 200){
            throw new Exception(result.getMsg());
        }
    }

    public void addBlacklist(Customer customer,Customer friend) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        UserModel blackUser = new UserModel().setId(customer.getAccountName());
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId(friend.getAccountName())
                .setBlacklist(blacklist);

        Result result = (Result)rongCloud.user.blackList.add(user);


        if(result.getCode() != 200){
            throw new Exception(result.getMsg());
        }
    }

    public void removeBlacklist(Customer customer,Customer friend) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        UserModel blackUser = new UserModel().setId(customer.getAccountName());
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId(friend.getAccountName())
                .setBlacklist(blacklist);
        Result result = (Result)rongCloud.user.blackList.remove(user);

        if(result.getCode() != 200){
            throw new Exception(result.getMsg());
        }
    }

    public BlackListResult getBlacklist(Customer customer) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        UserModel user = new UserModel().setId(customer.getAccountName());

        BlackListResult result = rongCloud.user.blackList.getList(user);

        return result;
    }
}
