package com.yunxin.cb.mall.web.action.message;


import com.alibaba.fastjson.JSON;
import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.imp.AttachmentService;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.cb.system.service.IMessageService;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageCenterController {

    private static Logger logger = LoggerFactory.getLogger(MessageCenterController.class);

    @Resource
    private IMessageService messageService;

    @Resource
    private AttachmentService attachmentService;

    @RequestMapping(value = "message")
    public String message( ModelMap modelMap) {
        return "message/messageCenter";
    }

    /**
     * 功能描述: 进入消息修改/新增页面
     *
     * @param: modelMap
     * @param messageId 消息ID
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:22
     */
    @RequestMapping(value = "toEditMessage")
    public String toEditMessage(ModelMap modelMap,@RequestParam(value = "messageId",required = false) int messageId){
        if(messageId == 0){
            //新增消息
            modelMap.addAttribute(new Message());
        }else{
            //修改消息
            //消息对象信息
            modelMap.addAttribute("message", messageService.getMessage(messageId));
            //消息摘要图片信息
            List<Attachment> listAttachment = attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.MESSAGEDIGEST,messageId);
            modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        }
        return "message/editMessage";
    }

    /**
     * 功能描述: 消息新增/修改
     *
     * @param: message form表单对象
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:21
     */
    @RequestMapping(value = "editMessage", method = RequestMethod.POST)
    public String editMessage(@ModelAttribute("message")Message message){
        //新增/修改的消息，推送状态均为未推送
        message.setPushStatus(PushStatus.HAVE_NOT_PUSHED);
        if(message.getMessageId() == 0){
            //新增消息
            message.setCreateTime(new Date());
        }
        try {
            messageService.addMessage(message);
        }catch (Exception e){
            if(message.getMessageId() == 0){
                logger.error("消息新增失败",e);
            }else{
                logger.error("消息修改失败",e);
            }
            return "redirect:../common/failure.do?reurl=message/editMessage.do";
        }
        return "redirect:../common/success.do?reurl=message/message.do";
    }

    /**
     * 功能描述: 消息中心-分页列表查询
     *
     * @param:query 查询对象
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:20
     */
    @RequestMapping(value = "pageMessage", method = RequestMethod.POST)
    @ResponseBody
    public Page<Message> pageMessage(@RequestBody PageSpecification<Message> query) {
        Page<Message> page = messageService.pageMessage(query);
        return page;
    }

    /**
     * 功能描述: 消息推送
     *
     * @param: messageId 消息ID
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:20
     */
    @RequestMapping(value = "pushMessage", method = RequestMethod.GET)
    public String pushMessage(@RequestParam(value = "messageId") int messageId, RedirectAttributes redirectAttributes){
        try {
            Message message = messageService.getMessage(messageId);
            if(null != message && message.getMessageId() > 0){
                //消息推送
                RongCloudService rongCloudService = new RongCloudService();
                if(rongCloudService.pushMessageToAll(message.getPushTitle())){
                    //状态更新
                    message.setPushStatus(PushStatus.HAVE_PUSHED);
                    message.setPushTime(new Date());
                    messageService.addMessage(message);
                    return "redirect:../common/success.do?reurl=message/message.do";
                }else {
                    redirectAttributes.addFlashAttribute("msgTitle","融云推送失败，请联系管理员！");
                }
            }
        }catch (Exception e){
            logger.error("消息推送失败",e);
        }
        return "redirect:../common/failure.do?reurl=message/message.do";
    }

    /**
     * 功能描述: 通过消息ID删除消息
     *
     * @param: messageId 消息ID
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:19
     */
    @RequestMapping(value = "removeMessageById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeMessageById(@RequestParam("messageId") int messageId) {
        if(messageId == 0){
            return false;
        }
        try {
            messageService.removeMessageById(messageId);
            return true;
        } catch (Exception e) {
            logger.error("消息删除失败",e);
            return false;
        }
    }

}
