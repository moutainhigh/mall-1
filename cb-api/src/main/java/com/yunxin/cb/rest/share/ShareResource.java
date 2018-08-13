package com.yunxin.cb.rest.share;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.redis.RedisService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.rest.customer.MainResource;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.system.vo.ShareInfo;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.yunxin.cb.meta.Result.SUCCESS;

/**
* @Description:    获取分享配置信息
* @Author:         likang
* @CreateDate:     2018/7/28 10:32
*/
@Api(description = "获取分享配置信息")
@RestController
@RequestMapping(value = "/share")
public class ShareResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(ShareResource.class);
    @Resource
    private IProfileService profileService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private RedisService redisService;

    @ApiOperation(value = "获取分享配置信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getShareInfo")
    public ResponseResult<ShareInfo> getShareInfo() {
        logger.info("loginId:"+getCustomerId());
        Customer customer = customerService.getCustomerById(getCustomerId());
        String invitationCode = "";
        if(null!=customer){
            invitationCode=customer.getInvitationCode();
        }
        logger.info("invitationCode:"+invitationCode);
        ShareInfo shareInfo = null;
        if(redisService.getKey("shareInfo")==null){
            shareInfo = profileService.getShareInfo(invitationCode);
        }else{
            shareInfo = (ShareInfo)redisService.getKey("shareInfo");
        }
        return new ResponseResult(SUCCESS, profileService.getShareInfo(invitationCode));
    }
}
