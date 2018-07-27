package com.yunxin.cb.mall.web.action.attachment;

import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @Description:    七牛云图片管理
* @Author:         likang
* @CreateDate:     2018/7/26 20:20
*/
@Controller
@RequestMapping(value = "/attachment")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class AttachmentController {

    @Resource
    private IAttachmentService attachmentService;


    /**
     * 页面跳转
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 20:21
     */
    @RequestMapping(value = "attachments")
    public String attachments(ModelMap modelMap) {
        return "attachment/attachments";
    }

    /**
     * 分页
     * @author      likang
     * @param attachmentQuery
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.Attachment>
     * @exception
     * @date        2018/7/17 19:48
     */
    @RequestMapping(value = "pageAttachment",method = RequestMethod.POST)
    @ResponseBody
    public Page<Attachment> pageAttachment(@RequestBody PageSpecification<Attachment> attachmentQuery) {
        return attachmentService.pageAttachment(attachmentQuery);
    }

}
