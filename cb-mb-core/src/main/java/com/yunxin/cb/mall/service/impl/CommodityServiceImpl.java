package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.mapper.AttachmentMapper;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.mapper.FavoriteMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.CommodityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @title: 商品接口实现类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Log log = LogFactory.getLog(CommodityServiceImpl.class);

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public Commodity selectByPrimaryKey(int commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    @Override
    public Map getCommdityDetail(int productId,int customerId) {
        Map resultMap = new HashMap();
        Product product = productMapper.selectProductById(productId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        if(product==null){
            return null;
        }
        Commodity commodity = commodityMapper.selectCommodityDetailById(product.getCommodityId(),ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        resultMap.put("product",product);//货品
        //resultMap.put("brand", commodity.getBrand());//品牌
        resultMap.put("priceSection", commodity.getPriceSection());//商品价格段
        resultMap.put("seller", commodity.getSeller());//商家
        Map specMap = new HashMap();//商品规格Map
        for (CommoditySpec spec : commodity.getCommoditySpecs()) {
            if(spec.getSpec().getSpecName().equals("级别")){
                resultMap.put("showLevel",spec.getValue());//级别（页面单独显示）
            }
            specMap.put(spec.getSpec().getSpecName(), spec.getValue());//将规格名称和规格内容封装
        }
        resultMap.put("specs", specMap);//规格及参数
        Map paymentMap=new HashMap();
        paymentMap.put(PaymentType.FULL_SECTION.ordinal(),PaymentType.FULL_SECTION.getName());
        paymentMap.put(PaymentType.LOAN.ordinal(),PaymentType.LOAN.toString());
        resultMap.put("paymentType", paymentMap);//支付方式
        Favorite favorite=null;
        if(customerId>0){//用户存在则查询商品收藏夹
            favorite=new Favorite();
            favorite.setCustomerId(customerId);
            favorite.setCommodityId(product.getCommodityId());
            favorite=favoriteMapper.findByCustomerAndCommodity(favorite);
        }
        //重组数据后清空
        commodity.setBrand(null);
        commodity.setSeller(null);
        commodity.setPriceSection(null);
        commodity.setCommoditySpecs(null);
        resultMap.put("favorite",favorite);//收藏夹
        resultMap.put("commodity",commodity);//商品
        List<Attachment> attachments=attachmentMapper.selectByObjectTypeAndId(ObjectType.COMMODITY.name(),commodity.getCommodityId());
        Set imageSet=new HashSet<>();
        for (Attachment attachment:attachments){
            imageSet.add(attachment.getFilePath());
        }
        resultMap.put("imageSet", imageSet);//商品图片
        return resultMap;
    }

    @Override
    public List<Product> getProductsByCommodityId(int commodityId) {
        List<Product> products = productMapper.selectAllByCommodityId(commodityId);
        return products;
    }

    @Override
    public List<Commodity> selectByBrandId(Integer brandId) {
        return commodityMapper.selectByBrandId(brandId);
    }

}
