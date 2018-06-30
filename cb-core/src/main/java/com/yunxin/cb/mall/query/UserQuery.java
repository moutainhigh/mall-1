package com.yunxin.cb.mall.query;

import com.yunxin.cb.console.entity.User;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author x001393
 */
public class UserQuery extends PageSpecification<User> {

    private int deptId;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }


}
