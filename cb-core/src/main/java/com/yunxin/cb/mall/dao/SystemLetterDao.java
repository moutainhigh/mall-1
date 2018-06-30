package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.SystemLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SystemLetterDao extends JpaRepository<SystemLetter, Integer>, JpaSpecificationExecutor<SystemLetter> {

//    @Modifying
//    @Query("update SystemLetter t set t.readed = true where t.letterId=?1")
//    void readMessage(int messageId);
//
//    long countByCustomerAndReaded(Customer customer,boolean readed);
}
