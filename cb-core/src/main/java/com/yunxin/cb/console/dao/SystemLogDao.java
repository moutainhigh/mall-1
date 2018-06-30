package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author x001393
 */
public interface SystemLogDao extends JpaRepository<SystemLog, Integer>, JpaSpecificationExecutor<SystemLog> {

    @Modifying
    @Query("delete from SystemLog slog where slog.userId=?1")
    public void removeSystemLogsByUserId(int userId);

}

