package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FiaciaLogDataVO;
import com.yunxin.cb.mall.vo.FiaciaLogVO;
import com.yunxin.cb.util.page.Query;

public interface FiaciaLogService {

    /**
     * @title: 添加账单记录
     * @param: [vo]
     * @return: int
     * @auther: eleven
     * @date: 2018/8/9 18:00
     */
    int addFiaciaLog(FiaciaLogVO vo);

    /**
     * @title: 分页查询用户账单
     * @param: [q]
     * @return: com.yunxin.cb.util.page.PageFinder<com.yunxin.cb.mall.vo.FiaciaLogDataVO>
     * @auther: eleven
     * @date: 2018/8/9 19:12
     */
    FiaciaLogDataVO pageFiaciaLog(Query q);
}
