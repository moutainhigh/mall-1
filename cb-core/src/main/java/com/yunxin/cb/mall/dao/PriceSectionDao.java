package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Aidy_He on 16/1/18.
 */
public interface PriceSectionDao extends JpaRepository<PriceSection, Integer>, JpaSpecificationExecutor<PriceSection>, BaseDao<PriceSection> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<PriceSection> findByEnabledOrderByStartPriceAsc(boolean enabled);
}
