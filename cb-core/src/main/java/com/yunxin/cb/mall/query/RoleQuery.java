package com.yunxin.cb.mall.query;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author x001393
 */
public class RoleQuery extends PageSpecification<Role> {

    private boolean showAll;

    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

}
