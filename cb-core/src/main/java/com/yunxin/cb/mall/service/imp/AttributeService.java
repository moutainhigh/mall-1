/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.cb.mall.service.IAttributeService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author j000101
 */
@Service
@Transactional
public class AttributeService implements IAttributeService {

    @Resource
    private AttributeDao attributeDao;

    @Resource
    private AttributeGroupDao attributeGroupDao;

    @Resource
    private CatalogAttributeDao catalogAttributeDao;

    @Resource
    private CatalogAttributeGroupDao catalogAttributeGroupDao;

    @Resource
    private IAttachmentService attachmentService;

    @Resource
    private ProductAttributeDao productAttributeDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CatalogAttribute getCatalogAttributeById(int attributeId) {
        return catalogAttributeDao.findOne(attributeId);
    }


    @Override
    public void removeCatalogAttributeById(int attributeId) {
        catalogAttributeDao.delete(attributeId);
    }

    @Override
    public void removeAttributeGroupById(int groupId) {
        attributeGroupDao.delete(groupId);
    }

    @Override
    public CatalogAttributeGroup addCatalogAttributeGroup(CatalogAttributeGroup attributeGroup) throws EntityExistException {
        if (!catalogAttributeGroupDao.isUnique(attributeGroup, CatalogAttributeGroup_.catalog, CatalogAttributeGroup_.groupName)) {
            throw new EntityExistException("属性组名称已存在");
        }
        attributeGroup.setCreateTime(new Date());
        attributeGroup = catalogAttributeGroupDao.save(attributeGroup);
        String[] attributeName = attributeGroup.getAttributeName();
        String[] imagePath = attributeGroup.getImagePath();
        int[] sortOrder = attributeGroup.getSortOrder();
        for (int i = 0; i < attributeName.length; i++) {
            CatalogAttribute attribute = new CatalogAttribute();
            attribute.setCatalogAttributeGroup(attributeGroup);
            attribute.setAttributeName(attributeName[i]);
            if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                attribute.setImagePath(imagePath[i]);
            }
            attribute.setSortOrder((short) sortOrder[i]);
            catalogAttributeDao.save(attribute);
        }
        return attributeGroup;
    }


    @Override
    public CatalogAttributeGroup updateCatalogAttributeGroup(CatalogAttributeGroup attributeGroup) throws EntityExistException {
        if (!catalogAttributeGroupDao.isUnique(attributeGroup, CatalogAttributeGroup_.catalog, CatalogAttributeGroup_.groupName)) {
            throw new EntityExistException("属性组名称已存在");
        }
        CatalogAttributeGroup oldAttributeGroup = catalogAttributeGroupDao.findOne(attributeGroup.getGroupId());
        AttributeReplication.copying(attributeGroup, oldAttributeGroup, CatalogAttributeGroup_.groupName, CatalogAttributeGroup_.showAsImage, CatalogAttributeGroup_.catalog);
        catalogAttributeDao.deleteByGroup(oldAttributeGroup);
        String[] attributeName = attributeGroup.getAttributeName();
        String[] imagePath = attributeGroup.getImagePath();
        int[] sortOrder = attributeGroup.getSortOrder();
        attachmentService.deleteAttachmentPictures(ObjectType.ATTRIBUTE,oldAttributeGroup.getGroupId());
        for (int i = 0; i < attributeName.length; i++) {
            CatalogAttribute attribute = new CatalogAttribute();
            attribute.setCatalogAttributeGroup(oldAttributeGroup);
            attribute.setAttributeName(attributeName[i]);
            if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                attribute.setImagePath(imagePath[i]);
            }
            attribute.setSortOrder((short) sortOrder[i]);
            attribute=catalogAttributeDao.save(attribute);
            if(imagePath!=null&&imagePath[i]!=null&&!"".equals(imagePath[i])){
                attachmentService.addAttachmentPictures(ObjectType.ATTRIBUTE,oldAttributeGroup.getGroupId(),imagePath[i]+",0,0");
            }
        }
        return attributeGroup;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CatalogAttributeGroup getCatalogAttributeGroupById(int groupId) {
        CatalogAttributeGroup attributeGroup = catalogAttributeGroupDao.findByIdFetchAll(groupId);
        return attributeGroup;
    }

    @Override
    public void removeCatalogAttributeGroupById(int groupId) {
        attachmentService.deleteAttachmentPictures(ObjectType.ATTRIBUTE,groupId);
        catalogAttributeGroupDao.delete(groupId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CatalogAttributeGroup> pageCatalogAttributeGroups(final PageSpecification<CatalogAttributeGroup> groupQuery) {
        groupQuery.setCustomSpecification(new CustomSpecification<CatalogAttributeGroup>() {
            @Override
            public void buildFetch(Root<CatalogAttributeGroup> root) {
                root.fetch(CatalogAttributeGroup_.catalog, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<CatalogAttributeGroup> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
            }
        });
        Page<CatalogAttributeGroup> pages = catalogAttributeGroupDao.findAll(groupQuery, groupQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CatalogAttributeGroup> getAttributeGroupsByCatalogId(final int catalogId) {
        return catalogAttributeGroupDao.findAttributeGroupFetchAttributesByCategoryId(catalogId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttributeGroup> getAttributeGroupsByCommodityId(final int commodityId) {
        return attributeGroupDao.findByCommodity_CommodityIdOrderByGroupIdAsc(commodityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findGroupIdsByAttributeIdIn(int[] attributeIds) {
        return attributeDao.findGroupIdsByAttributeIdIn(attributeIds);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<CatalogAttribute> findAttributeByGroupId(int groupId) {
        List<CatalogAttribute> list = catalogAttributeDao.findByCatalogAttributeGroup_GroupIdOrderBySortOrderAsc(groupId);
        return list;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductAttribute> findProductAttributeValues(int productId) {
        List<ProductAttribute> pps = productAttributeDao.findAllPropertyByProduct(productId);
        return pps;
    }

    @Override
    public void emptyProductAttribute(int productId) {
        productAttributeDao.emptyProductProperty(productId);
    }


    @Override
    public CatalogAttributeGroup getAttributeGroupWithCatalogsById(int groupId) {
        CatalogAttributeGroup attributeGroup = catalogAttributeGroupDao.findByIdFetchAll(groupId);
        return attributeGroup;
    }

    /**
     * 屈磊批量修改官网属性用
     *
     * @param propIds
     * @return
     */
    @Override
    public List<ProductAttribute> getProductPertiesByPropIdIn(List<Integer> propIds) {
        List<ProductAttribute> productPropties = productAttributeDao.findByProAttrIdIn(propIds);
        return productPropties;
    }

    /**
     * 屈磊批量修改官网属性用
     *
     * @param propId
     * @return
     */
    @Override
    public List<ProductAttribute> getProductPertiesByPropId(int propId) {
        List<ProductAttribute> productPropties = productAttributeDao.findByPropertyId(propId);
        return productPropties;
    }

    @Override
    public void addCommodityAttributeGroups(int commodityId, int[] groupIds) throws EntityExistException {
        Commodity commodity = new Commodity(commodityId);
        Date date = new Date();
        for (int groupId : groupIds) {
            CatalogAttributeGroup ca = catalogAttributeGroupDao.findOne(groupId);
            AttributeGroup existsGroup = attributeGroupDao.checkUniqueByGroupNameAndCommodityId(ca.getGroupName(), commodityId);
            if (null != existsGroup) {
                throw new EntityExistException("属性组名称已存在");
            }
            AttributeGroup group = new AttributeGroup();
            group.setCommodity(commodity);
            group.setGroupName(ca.getGroupName());
            group.setShowAsImage(ca.isShowAsImage());
            group.setCreateTime(date);
            group = attributeGroupDao.save(group);
            for (CatalogAttribute catalogAttribute : ca.getCatalogAttributes()) {
                Attribute attribute = new Attribute();
                attribute.setAttributeGroup(group);
                attribute.setAttributeName(catalogAttribute.getAttributeName());
                attribute.setImagePath(catalogAttribute.getImagePath());
                attribute.setSortOrder(catalogAttribute.getSortOrder());
                attributeDao.save(attribute);
            }
        }
    }

    @Override
    public AttributeGroup addAttributeGroup(AttributeGroup attributeGroup) throws EntityExistException {
        if (!attributeGroupDao.isUnique(attributeGroup, AttributeGroup_.groupName)) {
            throw new EntityExistException("属性组名称已存在");
        }
        attributeGroup.setCreateTime(new Date());
        attributeGroup = attributeGroupDao.save(attributeGroup);
        String[] attributeName = attributeGroup.getAttributeName();
        String[] imagePath = attributeGroup.getImagePath();
        int[] sortOrder = attributeGroup.getSortOrder();
        attachmentService.deleteAttachmentPictures(ObjectType.ATTRIBUTE,attributeGroup.getGroupId());
        for (int i = 0; i < attributeName.length; i++) {
            Attribute attribute = new Attribute();
            attribute.setAttributeGroup(attributeGroup);
            attribute.setAttributeName(attributeName[i]);
            if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                attribute.setImagePath(imagePath[i]);
            }
            attribute.setSortOrder((short) sortOrder[i]);
            attributeDao.save(attribute);
            if(imagePath!=null&&imagePath[i]!=null&&!"".equals(imagePath[i])){
                attachmentService.addAttachmentPictures(ObjectType.ATTRIBUTE,attributeGroup.getGroupId(),imagePath[i]+",0,0");
            }
        }
        return attributeGroup;
    }


    @Override
    public AttributeGroup updateAttributeGroup(AttributeGroup attributeGroup) throws EntityExistException {
        if (!attributeGroupDao.isUnique(attributeGroup, AttributeGroup_.groupName)) {
            throw new EntityExistException("属性组名称已存在");
        }
        AttributeGroup oldAttributeGroup = attributeGroupDao.findByGroupId(attributeGroup.getGroupId());
        AttributeReplication.copying(attributeGroup, oldAttributeGroup, AttributeGroup_.groupName, AttributeGroup_.showAsImage);
        int[] attributeId = attributeGroup.getAttributeId();
        Set<Attribute> attributes = oldAttributeGroup.getAttributes();
        attributes.stream().forEach(p -> {
            boolean exist = false;
            for (int id : attributeId) {
                if (id == p.getAttributeId()) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {//如果已不存在，删这个属性值
                attributeDao.delete(p);
            }
        });
        String[] attributeName = attributeGroup.getAttributeName();
        String[] imagePath = attributeGroup.getImagePath();
        int[] sortOrder = attributeGroup.getSortOrder();
        for (int i = 0; i < attributeName.length; i++) {
            String imgPath = "";
            if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                imgPath = imagePath[i];
            }
            if (attributeId.length>i&&attributeId[i] > 0) {
                Attribute attribute = attributeDao.findOne(attributeId[i]);
                attribute.setAttributeName(attributeName[i]);
                attribute.setImagePath(imgPath);
                attribute.setSortOrder((short) sortOrder[i]);
                if(imgPath!=null&&!"".equals(imgPath)){
                    attachmentService.deleteAttachmentPictures(ObjectType.ATTRIBUTE,attribute.getAttributeId());
                    attachmentService.addAttachmentPictures(ObjectType.ATTRIBUTE,attribute.getAttributeId(),imgPath+",0,0");
                }
            } else {
                Attribute attribute = new Attribute();
                attribute.setAttributeGroup(attributeGroup);
                attribute.setAttributeName(attributeName[i]);
                attribute.setImagePath(imgPath);
                attribute.setSortOrder((short) sortOrder[i]);
                attributeDao.save(attribute);
                if(imgPath!=null&&!"".equals(imgPath)){
                    attachmentService.deleteAttachmentPictures(ObjectType.ATTRIBUTE,attribute.getAttributeId());
                    attachmentService.addAttachmentPictures(ObjectType.ATTRIBUTE,attribute.getAttributeId(),imgPath+",0,0");
                }
            }
        }
        return oldAttributeGroup;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public AttributeGroup getAttributeGroupById(int groupId) {
        return attributeGroupDao.findByGroupId(groupId);
    }

}
