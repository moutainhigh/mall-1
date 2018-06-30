package com.yunxin.cb.mall.dao;


import com.yunxin.cb.mall.entity.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author x001393
 */
public interface KeyWordDao extends JpaRepository<KeyWord, Integer>, JpaSpecificationExecutor<KeyWord> {

    @Query("select k from KeyWord k where k.keyKey=?1")
    public KeyWord findByKey(String keyKey);

    public KeyWord findByKeyValue(String keyValue);

    @Query("from KeyWord where keyValue=?1 and keyWordId<>?2")
    public KeyWord findByKeyValueAndKeyWordIdNot(String keyValue, int keyWordId);

}
