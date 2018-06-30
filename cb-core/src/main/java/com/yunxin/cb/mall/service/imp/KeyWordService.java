package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.KeyWordDao;
import com.yunxin.cb.mall.entity.KeyWord;
import com.yunxin.cb.mall.service.IKeyWordService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * @author x001393
 */
@Service
@Transactional
public class KeyWordService implements IKeyWordService {

    private static final Logger logger = LoggerFactory.getLogger(KeyWordService.class);

    @Resource
    private KeyWordDao keyWordDao;

    @Override
    public KeyWord addKeyWord(KeyWord keyWord) {
        StringBuffer keyCode = new StringBuffer(32);
        keyCode.append(keyWord.getKeyValue()).append(DateUtils.getFormatTimestamp(new Date()));
        String code = String.valueOf(keyCode.toString().hashCode());
        Integer code2 = Math.abs(Integer.parseInt(code)); //取code绝对值
        keyWord.setKeyKey(String.valueOf(code2));
        return keyWordDao.save(keyWord);
    }

    @Override
    public KeyWord updateKeyWord(KeyWord keyWord) {
        KeyWord dbKeyWord = keyWordDao.findOne(keyWord.getKeyWordId());
        dbKeyWord.setKeyValue(keyWord.getKeyValue());
        return dbKeyWord;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public KeyWord getKeyWordByKey(String keyKey) {
        KeyWord keyWord = keyWordDao.findByKey(keyKey);
        return keyWord;
    }

    @Override
    public void removeKeyWord(int keyWordId) {
        keyWordDao.delete(keyWordId);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<KeyWord> pageKeyWords(final PageSpecification<KeyWord> keyQuery) {
        keyQuery.setCustomSpecification(new CustomSpecification<KeyWord>() {
            @Override
            public void addConditions(Root<KeyWord> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<KeyWord> keyWords = keyWordDao.findAll(keyQuery,
                keyQuery.getPageRequest());
        return keyWords;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public KeyWord getKeyWordById(int keyWordId) {
        return keyWordDao.findOne(keyWordId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<KeyWord> getAllKeyWords() {
        return keyWordDao.findAll();
    }

}
