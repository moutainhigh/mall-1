package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.core.orm.BaseDaoImpl;

import javax.persistence.Query;
import java.util.List;

public class FreightDaoImpl extends BaseDaoImpl<Freight> implements FreightPlusDao {

    @Override
    public List<Freight> listFreightByPcode(String pcode) {
        String jpql = "from Freight f where f.areaCode like :areaCode1 and f.areaCode<> :areaCode2";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("areaCode1", pcode);
        String areaCode2 = "";
        if (pcode.endsWith("00")) {
            areaCode2 = pcode.substring(0, 2) + "0000";
        }
        if (pcode.endsWith("%")) {
            areaCode2 = pcode.substring(0, 4) + "00";
        }
        query.setParameter("areaCode2", areaCode2);
        List<Freight> freights = query.getResultList();
        return freights;
    }

}
