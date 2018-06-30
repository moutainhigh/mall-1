package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.KeyWord;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author x001393
 */
public interface IKeyWordService {

    public KeyWord addKeyWord(KeyWord keyWord);

    public KeyWord updateKeyWord(KeyWord keyWord);

    public KeyWord getKeyWordByKey(String keyKey);

    public KeyWord getKeyWordById(int keyWordId);

    public void removeKeyWord(int keyWordId);

    Page<KeyWord> pageKeyWords(PageSpecification<KeyWord> query);

    List<KeyWord> getAllKeyWords();

}
