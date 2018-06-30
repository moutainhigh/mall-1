/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.cb.mall.entity.CatalogAttribute;
import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.cb.mall.entity.ProductAttribute;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author j000101
 */
public interface IAttributeService {


    /**
     * 根据ID查询属性
     *
     * @param attributeId
     * @return
     */
    public CatalogAttribute getCatalogAttributeById(int attributeId);


    /**
     * 根据ID删除属性
     *
     * @param attributeId
     */
    public void removeCatalogAttributeById(int attributeId);


    void removeAttributeGroupById(int groupId);

    /**
     * 添加属性组
     *
     * @param attributeGroup
     * @return
     */
    public CatalogAttributeGroup addCatalogAttributeGroup(CatalogAttributeGroup attributeGroup) throws EntityExistException;

    /**
     * 修改属性组
     *
     * @param attributeGroup
     * @return
     */
    public CatalogAttributeGroup updateCatalogAttributeGroup(CatalogAttributeGroup attributeGroup) throws EntityExistException;

    /**
     * 根据ID查询属性组
     *
     * @param groupId
     * @return
     */
    public CatalogAttributeGroup getCatalogAttributeGroupById(int groupId);

    /**
     * 删除属性组
     *
     * @param groupId
     */
    public void removeCatalogAttributeGroupById(int groupId);

    /**
     * 分页查询属性组
     *
     * @param query
     * @return
     */
    public Page<CatalogAttributeGroup> pageCatalogAttributeGroups(PageSpecification<CatalogAttributeGroup> query);


    @Transactional(readOnly = true)
    List<AttributeGroup> getAttributeGroupsByCommodityId(int commodityId);

    @Transactional(readOnly = true)
    List<Integer> findGroupIdsByAttributeIdIn(int[] attributeIds);


    /**
     * 根据属性组查询属性
     *
     * @param groupId
     * @return
     * @throws Exception
     */
    public List<CatalogAttribute> findAttributeByGroupId(int groupId);


    /**
     * 根据货品ID查询货品关联属性
     *
     * @param productId
     * @return
     * @throws Exception
     */
    public List<ProductAttribute> findProductAttributeValues(int productId);

    /**
     * 清空货品属性
     *
     * @param productId
     * @throws Exception
     */
    public void emptyProductAttribute(int productId);


    public CatalogAttributeGroup getAttributeGroupWithCatalogsById(int groupId);


    //屈磊批量修改官网属性用
    List<ProductAttribute> getProductPertiesByPropIdIn(List<Integer> propIds);

    //屈磊批量修改官网属性用
    List<ProductAttribute> getProductPertiesByPropId(int propId);


    /**
     * 根据分类ID获取属性组及属性组下的属性
     *
     * @param catalogId
     * @return
     */
    public List<CatalogAttributeGroup> getAttributeGroupsByCatalogId(int catalogId);

    void addCommodityAttributeGroups(int commodityId, int[] groupIds) throws EntityExistException;

    AttributeGroup addAttributeGroup(AttributeGroup attributeGroup) throws EntityExistException;

    AttributeGroup updateAttributeGroup(AttributeGroup attributeGroup) throws EntityExistException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    AttributeGroup getAttributeGroupById(int groupId);
}
