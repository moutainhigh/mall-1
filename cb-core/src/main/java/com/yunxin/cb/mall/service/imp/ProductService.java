/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.IProductService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.core.web.HtmlEscape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @author gonglei
 */
@Service
@Transactional
public class ProductService implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductDao productDao;

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private CatalogDao catalogDao;


    @Resource
    private ProductAttributeDao productAttributeDao;


    @Resource
    private AttributeDao attributeDao;

    @Override
    public Product addProduct(Product product) {
        product.setCreateTime(new Date());
        product.setProductName("");
        product.setProductState(ProductState.AUDITED);
        product.setPublishState(PublishState.WAIT_UP_SHELVES);
        product = productDao.save(product);
        int[] attributeIds = product.getAttributeIds();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributeIds.length; i++) {
            Attribute attribute = attributeDao.findByAttributeId(attributeIds[i]);
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setProduct(product);
            productAttribute.setAttribute(attribute);
            productAttributeDao.save(productAttribute);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(attribute.getAttributeGroup().getGroupName());
            sb.append("：").append(attribute.getAttributeName());
        }
        product.setProductName(sb.toString());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product dbProduct = productDao.findOne(product.getProductId());
        AttributeReplication.copying(product, dbProduct, Product_.productNo, Product_.costPrice, Product_.salePrice, Product_.marketPrice, Product_.weight,
                Product_.volume, Product_.store, Product_.storeNum,Product_.defaultPicPath);
        return dbProduct;
    }

    @Override
    public Product writeReason(Product prodcut) {
        Product product1 = productDao.findOne(prodcut.getProductId());
        product1.setRemark(prodcut.getRemark());
        product1.setProductState(ProductState.NOT_AUDITED);
        return product1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductById(int productId) {
        // return productDao.findOne(productId);
        Product product = productDao.finByProductId(productId);
        if (LogicUtils.isNotNull(product)) {
//            List<Attribute > pvs = propertyService.getPropertyValueByProductId(productId);
//            if (LogicUtils.isNotNullAndEmpty(pvs)) {
//                String s = "";
//                for (Attribute pv : pvs) {
//                    if (!product.getProductName().contains(pv.getValueName()) && !s.contains(pv.getValueName()))
//                        s += " " + pv.getValueName();
//                }
//                s = product.getProductName() + s;
//                product.setPropValueName(s);
//            }
        }
        return product;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductDetailById(int productId) {
        Product product = productDao.findProductDetail(productId);
        String content = product.getCommodity().getContent();
        product.getCommodity().setContent(HtmlEscape.decodeHtml(content));
        return product;
    }

    @Override
    public void removeProductById(int productId) {
        productDao.delete(productId);
    }

    @Override
    public void updateProductStatusById(int productId, ProductState status) {
        productDao.updateProductStatusById(status, productId);

    }


    @Override
    public Page<Product> pageProducts(PageSpecification<Product> query) {
        query.setCustomSpecification(new CustomSpecification<Product>() {
            @Override
            public void addConditions(Root<Product> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Product_.createTime)));
            }

        });
        Page<Product> pages = productDao.findAll(query,
                query.getPageRequest());
        return pages;
    }


    @Override
    public void updateProductByProductId(int depositoryNum, int productId) {
        productDao.updateProductByProductId(depositoryNum, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsInIds(List<Integer> productIds) {
        return productDao.findByProductIdIn(productIds);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getProductsByAddTime(int limit) {
        PageRequest pr = new PageRequest(0, limit);
        return productDao.findByOrderByAddTime_desc(pr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getProductsByCommodity(String categoryName) {
        PageRequest pr = new PageRequest(0, 8);
        return productDao.findByCommodity(categoryName, pr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getProductsByCommodity(Commodity commodity) {
        return productDao.findByCommodityOrderByProductNameAsc(commodity);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Product> getProductsFetchAllByCommodityId(int commodityId, PublishState publishState) {
        return productDao.findByCommodityOrderByProductNameAsc(commodityId, publishState);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Product> getProductsFetchAllByCommodityId(int commodityId) {
        return productDao.findByCommodityOrderByProductNameAsc(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Product> getProductsByCommodityAndStatus(Commodity commodity,
                                                         ProductState status) {
        return productDao.findByCommodityAndProductStateOrderByProductNameAsc(
                commodity, status);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getSameBrandProductsByProductId(int productId) {
        return productDao.findSameBrandProductsByBrandId(productDao
                .findBrandIdByProductId(productId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> findBySalesNum(String categoryName) {
        Catalog categoty = catalogDao.findTopByCatalogName(categoryName);
        return productDao.findBySalesNum(categoty, 5);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getSameCategoryProductsByProductId(int productId) {
        return productDao.findSameCategoryProductsByCategoryId(productDao
                .findCategoryIdByProductId(productId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductByProductName(String productName) {
        Product product = productDao.findByProductName(productName);
        return product;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductByProductNameAndStatusNot(String productName, ProductState status) {
        Product product = productDao.findByProductNameAndProductStateNot(productName,
                status);
        return product;
    }


    @Override
    public Product generateProductByCommodity(Commodity commodity, String[] propValues) {
        Product prod = new Product();
        Date now = new Date();
        prod.setAddTime(now);
        prod.setCreateTime(now);
        prod.setDefaultPicPath(commodity.getDefaultPicPath());
        String code = commodity.getCommodityId() + CommonUtils.randomString(8, CommonUtils.RANDRULE.RAND_UPPER);
        prod.setProductName(commodity.getCommodityName() + CommonUtils.randomString(2, CommonUtils.RANDRULE.RAND_UPPER));
        prod.setProductState(ProductState.AUDITED);
        prod.setPublishState(PublishState.UP_SHELVES);
//        prod.setPropvalues(propValues);
        prod.setCommodity(commodity);
        return addProduct(prod);
    }


    @Override
    public void deleteProduct(int productId) {
        Product product = productDao.finByProductId(productId);
        productDao.delete(product);
    }

    @Override
    public Product setProductDefaultImgPathNull(int productId) {
        Product product = productDao.findOne(productId);
        product.setDefaultPicPath(null);
        return product;
    }

    @Override
    public Product findOne(int productId) {
        Product product = productDao.findOne(productId);
        return product;
    }

    @Override
    public List<Product> findByStatusAndCommodity_commodityId(ProductState productState, int commodityId) {
        List<Product> productList = productDao.findByProductStateAndCommodity_commodityId(ProductState.AUDITED, commodityId);
        return productList;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getProductsByProductNameInAndProductStatusNot(String[] productNames, ProductState productState) {
        List<Product> productList = productDao.findByProductNameInAndProductStateNot(productNames, productState);
        return productList;
    }

    @Override
    public void addProduct(List<Product> productList) {
        if (LogicUtils.isNotNullAndEmpty(productList)) {
            for (Product product : productList) {
                this.addProduct(product);
            }
        }
    }

    @Override
    public boolean upOrDownShelvesProduct(int productId, PublishState publishState) {
        Product product = productDao.findProductDetail(productId);
        if (product.getProductState() != ProductState.AUDITED) {
            return false;
        }
        Commodity commodity = product.getCommodity();
        if ((product.getPublishState() == PublishState.WAIT_UP_SHELVES || product.getPublishState() == PublishState.DOWN_SHELVES)
                && publishState == PublishState.UP_SHELVES) {
            productDao.updateUpOrDownShelvesInProductId(PublishState.UP_SHELVES, new int[]{productId});
            //给商品设置默认货品
            if (commodity.getDefaultProduct() == null) {
                commodity.setDefaultProduct(product);
                commodityDao.updateDefaultProductById(product, product.getCommodity().getCommodityId());
            }
            return true;
        } else if ((product.getPublishState() == PublishState.WAIT_UP_SHELVES || product.getPublishState() == PublishState.UP_SHELVES)
                && publishState == PublishState.DOWN_SHELVES) {
            if (commodity.getDefaultProduct().getProductId() == productId) {//如果是默认货品下架
                Product defaultProduct=null;//默认货品id
                for(Product pro:commodity.getProducts()){//遍历所有货品
                    if(pro.getProductId()!=productId&&pro.getPublishState()==PublishState.UP_SHELVES){//默认取该商品还在上架的第一个货品为默认货品
                        defaultProduct=pro;
                        break;
                    }
                }
                commodityDao.updateDefaultProductById(defaultProduct, commodity.getCommodityId());
            }
            productDao.updateUpOrDownShelvesInProductId(PublishState.DOWN_SHELVES, new int[]{productId});
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getProductCount() {
        return productDao.count();
    }


}
