package com.yunxin.cb.mall.web.action.message;


import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.cb.system.service.IMessageService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/message")
public class MessageCenterController {
    @Resource
    private IMessageService messageService;

    @RequestMapping(value = "message")
    public String message( ModelMap modelMap) {
        return "message/messageCenter";
    }

    /**
     * 进入消息修改/新增页面
     * @param modelMap
     * @param messageId 消息ID
     * @return
     */
    @RequestMapping(value = "toEditMessage")
    public String toEditMessage(ModelMap modelMap,@RequestParam(value = "messageId",required = false) int messageId){
        if(messageId > 0){
            modelMap.addAttribute("message", messageService.getMessage(messageId));
        }else{
            modelMap.addAttribute(new Message());
        }
        return "message/editMessage";
    }

    /**
     * 消息新增/修改
     * @param message
     * @return
     */
    @RequestMapping(value = "editMessage", method = RequestMethod.POST)
    public String editMessage(@ModelAttribute("message")Message message){
        if(message.getMessageId() == 0){
            message.setCreateTime(new Date());
            message.setPushStatus(PushStatus.HAVE_NOT_PUSHED);
        }
        messageService.addMessage(message);
        return "redirect:../common/success.do?reurl=message/message.do";
    }

    /**
     * 消息中心分页信息
     * @param query
     * @return
     */
    @RequestMapping(value = "pageMessage", method = RequestMethod.POST)
    @ResponseBody
    public Page<Message> pageMessage(@RequestBody PageSpecification<Message> query) {
        Page<Message> page = messageService.pageMessage(query);
        return page;
    }

    /**
     * 消息推送
     * @param messageId 消息ID
     * @return
     */
    @RequestMapping(value = "pushMessage", method = RequestMethod.GET)
    public String pushMessage(@RequestParam(value = "messageId") int messageId){
        Message message = messageService.getMessage(messageId);
        if(null != message && message.getMessageId() > 0){
            //消息推送
            RongCloudService rongCloudService = new RongCloudService();
            rongCloudService.pushMessageToAll(message.getPushTitle());

            //状态更新
            message.setPushStatus(PushStatus.HAVE_PUSHED);
            message.setPushTime(new Date());
            messageService.addMessage(message);
        }
        return "redirect:../common/success.do?reurl=message/message.do";
    }

}
