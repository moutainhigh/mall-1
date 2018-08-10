package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.ReimbursementProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReimbursementProcessDao  extends JpaRepository<ReimbursementProcess, Integer>, JpaSpecificationExecutor<ReimbursementProcess> {

    @Query("select c from ReimbursementProcess c left join fetch c.reimbursement d where d.reimbursementId=?1 order by c.createTime desc ")
    public List<ReimbursementProcess> getReimbursementProcessByRe(int reimbursementId);

}

