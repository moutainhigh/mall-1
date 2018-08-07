package com.yunxin.cb.rest.rb;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.sms.SendType;
import com.yunxin.cb.sms.SmsConfig;
import com.yunxin.cb.sms.SmsHelper;
import com.yunxin.cb.util.CommonUtils;
import com.yunxin.cb.util.VerificationCode;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(description = "手机验证码")
@RestController
@RequestMapping(value = "/{version}/rb/mobile")
public class MobileVaildCode {


    @ApiOperation(value = "发送验证码")
    @PostMapping("sendMobileValidCode/{sendType}/{mobile}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号码", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "sendType", value = "验证码类型", required = true, paramType = "path", dataType = "String")
    })
    public ResponseResult sendMobileValidCode(@PathVariable SendType sendType, @PathVariable String mobile, HttpSession session, HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult(Result.FAILURE);
        boolean isSend = false;
        //判断时间，1分钟只允许发送一次
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(mobile);
        // session.getAttribute(mobile);
        if (verificationCode != null && (System.currentTimeMillis() - verificationCode.getSendTime()) < 60000) {
            responseResult.setMessage("发送过于频繁，请稍后再试！");
            return responseResult;
        }
        switch (sendType) {
        }
        try {
            String randomCode = CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_NUMBER);
            String content=SmsConfig.VALID_CODE_CONTENT.replace("#XXXXXX#",randomCode);
            boolean sendState = SmsHelper.sendMobileValidCode(getIpAddr(request), content, mobile);
            if (sendState) {

                responseResult.setResult(Result.SUCCESS);
                responseResult.setMessage(randomCode);
                VerificationCode mobileCode = new VerificationCode(mobile, randomCode, System.currentTimeMillis());
                CachedUtil.getInstance().setContext(mobile, mobileCode);
            } else {
                responseResult.setMessage("短信发送失败，请确认手机号或稍后再试!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setMessage("服务器出错！");
        }
        return responseResult;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
