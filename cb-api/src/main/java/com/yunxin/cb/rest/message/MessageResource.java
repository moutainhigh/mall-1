package com.yunxin.cb.rest.message;

import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.rest.customer.MainResource;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.service.imp.MessageService;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.core.persistence.PageSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息中心
 * create by yangzhen
 */
@Api(description = "消息中心接口")
@RestController
@RequestMapping(value = "/message")
public class MessageResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(MainResource.class);

    @Resource
    private MessageService messageService;

    /**
     *
     * 功能描述: 查询消息列表
     *
     * @param: query
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/10 18:28
     */
    @ApiOperation(value = "查询消息列表")
    @ApiImplicitParams({})
    @PostMapping(value = "getMessages")
    public ResponseResult getMessages(@RequestBody PageSpecification<Message> query) {
        PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
        filterDescriptor.setField("message.messageId");
        filterDescriptor.setLogic("and");
        filterDescriptor.setOperator("eq");
        filterDescriptor.setValue(getCustomerId());
        query.getFilter().getFilters().add(filterDescriptor);
        Page<Message> pagelist = messageService.pageMessage(query);
        return new ResponseResult(pagelist);

    }

    /**
     * 功能描述: 查询消息详情
     *
     * @param: messageId 消息ID
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/10 18:27
     */
    @ApiOperation(value = "查询消息详情")
    @RequestMapping(value = "getMessageInfo")
    @ResponseBody
    public ResponseResult getMessageInfo(@RequestParam("messageId") int messageId) {
        Message  message = messageService.getMessage(messageId);
        return new ResponseResult(message);
    }

}
