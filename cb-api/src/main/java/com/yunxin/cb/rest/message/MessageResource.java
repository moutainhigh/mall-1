package com.yunxin.cb.rest.message;

import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 消息中心
 * create by yangzhen
 */
@Api(description = "消息中心接口")
@RestController
@RequestMapping(value = "/message")
public class MessageResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Resource
    private MessageService messageService;

    /**
     * 功能描述: 查询消息列表
     *
     * @param: query
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/10 18:28
     */
    @ApiOperation(value = "查询消息列表")
    @ApiImplicitParams({})
    @RequestMapping(value = "getMessages" , method = RequestMethod.POST)
    public ResponseResult getMessages(@RequestParam("pageSize")int pageSize, @RequestParam("pageNo")int pageNo) {
        PageSpecification<Message> query = new PageSpecification<>();
        query.setPage(pageNo == 0?1:pageNo);
        query.setPageSize(pageSize == 0?10:pageSize);
        PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
        filterDescriptor.setLogic("and");
        filterDescriptor.setOperator("eq");
        //APP接口仅查询状态为已推送的消息列表
        filterDescriptor.setField("pushStatus");
        filterDescriptor.setValue(PushStatus.HAVE_PUSHED);
        query.getFilter().getFilters().add(filterDescriptor);
        try {
            Page<Message> oldPage = messageService.pageMessage(query);

            //适配其他模块接口返回的数据格式
            Map<String,Object> newPageMap = new HashMap<>();
            newPageMap.put("pageSize",pageSize);
            newPageMap.put("rowCount",oldPage.getTotalElements());
            newPageMap.put("pageCount",oldPage.getTotalPages());
            newPageMap.put("pageNo",pageNo);
            newPageMap.put("hasPrevious",true);
            newPageMap.put("hasNext",oldPage.getTotalPages() > pageNo);
            newPageMap.put("startOfPage",(pageNo - 1) * pageSize);
            newPageMap.put("data",oldPage.getContent());

            return new ResponseResult(newPageMap);
        }catch (Exception e){
            logger.error("查询消息列表失败",e);
            return new ResponseResult(Result.FAILURE,"查询消息列表失败");
        }
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
    @RequestMapping(value = "getMessageInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getMessageInfo(@RequestParam("messageId") int messageId) {
        if(messageId == 0){
            return new ResponseResult(Result.FAILURE);
        }
        try {
            Message message = messageService.getMessage(messageId);
            return new ResponseResult(message);
        }catch (Exception e){
            logger.error("查询消息详情失败",e);
            return new ResponseResult(Result.FAILURE,"查询消息详情失败");
        }
    }

}
