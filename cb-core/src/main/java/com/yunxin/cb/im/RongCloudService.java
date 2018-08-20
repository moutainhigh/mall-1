package com.yunxin.cb.im;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.mall.entity.Customer;
import io.rong.RongCloud;
import io.rong.messages.CmdMsgMessage;
import io.rong.messages.ContactNtfMessage;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.SystemMessage;
import io.rong.models.response.BlackListResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RongCloudService {

    private static Logger logger = LoggerFactory.getLogger(RongCloudService.class);

    @Value("${rongcloud.appKey}")
    private String appKey = "z3v5yqkbz1jp0";

    @Value("${rongcloud.appSecret}")
    private String appSecret = "AUUwTO6vXg7y7P";

    public String register(Customer customer) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;
        String nickName = StringUtils.isNotBlank(customer.getNickName()) ? customer.getNickName() : customer.getAccountName();
        UserModel user = new UserModel()
                .setId(customer.getAccountName())
                .setName(nickName)
                .setPortrait(customer.getAvatarUrl());

        TokenResult result = User.register(user);
        if (result.getCode() == 200) {
            logger.info("getRongCloudToken:  " + result.toString());
            return result.getToken();
        } else {
            throw new Exception(result.getMsg());
        }
    }

    public void update(Customer customer) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;
        String nickName = StringUtils.isNotBlank(customer.getNickName()) ? customer.getNickName() : customer.getAccountName();
        UserModel user = new UserModel()
                .setId(customer.getAccountName())
                .setName(nickName)
                .setPortrait(customer.getAvatarUrl());

        Result result = User.update(user);
        if (result.getCode() != 200) {
            throw new Exception(result.getMsg());
        }
    }

    public void sendMessage(Customer customer, Customer friend, String requestMessage) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        String content = customer.getNickName() + "请求加您为好友";
        ContactNtfMessage txtMessage = new ContactNtfMessage("Request", customer.getNickName(), customer.getAccountName(), friend.getAccountName(), content);
        SystemMessage systemMessage = new SystemMessage()
                .setSenderId(customer.getAccountName())
                .setTargetId(new String[]{friend.getAccountName()})
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent(content)
                .setPushData("{'pushData':'" + requestMessage + "'}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);
        ResponseResult result = rongCloud.message.system.send(systemMessage);


        if (result.getCode() != 200) {
            throw new Exception(result.getMsg());
        }
    }

    public void sendPrivateMessage(String senderId, String targetId, String cmd) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        CmdMsgMessage txtMessage = new CmdMsgMessage(cmd, cmd);
        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderId(senderId)
                .setTargetId(new String[]{targetId})
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage);
        ResponseResult result = rongCloud.message.msgPrivate.send(privateMessage);
        if (result.getCode() != 200) {
            throw new Exception(result.getMsg());
        }
    }

    public void addBlacklist(String customer, String friend) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        UserModel blackUser = new UserModel().setId(friend);
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId(customer)
                .setBlacklist(blacklist);

        Result result = (Result) rongCloud.user.blackList.add(user);

        if (result.getCode() != 200) {
            throw new Exception(result.getMsg());
        }
    }

    public void removeBlacklist(String customer, String friend) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        UserModel blackUser = new UserModel().setId(friend);
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId(customer)
                .setBlacklist(blacklist);
        Result result = (Result) rongCloud.user.blackList.remove(user);

        if (result.getCode() != 200) {
            throw new Exception(result.getMsg());
        }
    }

    public BlackListResult getBlacklist(String customer) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        UserModel user = new UserModel().setId(customer);
        BlackListResult result = rongCloud.user.blackList.getList(user);
        return result;
    }

    public boolean groupCreate(String customerId,String groupId,String groupName) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        GroupModel groupModel=new GroupModel(){
            {
                setId(groupId);
                setName(groupName);
                GroupMember[] groupMember=new GroupMember[1];
                groupMember[0]=new GroupMember(){
                    {
                        setGroupId(groupId);
                        setId(customerId);
                        setName(groupName);
                    }
                };
                setMembers(groupMember);
            }
        };
        Result result = (Result)rongCloud.group.create(groupModel);
        if (result.getCode() != 200)
            return true;
        else
            return false;
    }

    /**
     * 推送消息（向应用内所有用户推送消息）
     * @param content
     */
    public boolean pushMessageToAll(String content){
        boolean oppStatus = false;
        if(StringUtils.isEmpty(content)){
            return oppStatus;
        }
        String url = "https://api.cn.ronghub.com/push.json";
        //{"platform":["ios","android"],"audience":{"is_to_all":true},"notification":{"alert":"this is a push"}}
        String jsonParam = "{\"platform\":[\"ios\",\"android\"],\"audience\":{\"is_to_all\":true},\"notification\":{\"alert\":\"" + content + "\"}}";
        try {
            String jsonResult = RongCloudUtils.post(appKey,appSecret,url, jsonParam, "UTF-8", 20000);
            if(!StringUtils.isEmpty(jsonResult)){
                if(200 == JSONObject.parseObject(jsonResult).getIntValue("code")){
                    oppStatus = true;
                }
            }
        } catch (Exception e) {
            logger.error("推送消息异常",e);
        }
        return oppStatus;
    }

}