package com.yunxin.cb.im;

import com.yunxin.cb.mall.entity.Customer;
import io.rong.RongCloud;
import io.rong.methods.user.User;
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
                .setPortrait("http://pb9sg55i7.bkt.clouddn.com/avatar.png");

        TokenResult result = User.register(user);
        if(result.getCode() == 200){
            logger.info("getRongCloudToken:  " + result.toString());
            return result.getToken();
        }else {
            throw new Exception(result.getMsg());
        }
    }
}
