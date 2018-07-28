package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.CommoditySpec;
import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.mapper.AttachmentMapper;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.mapper.FavoriteMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public CommodityVo getCommdityDetail(int productId,int customerId) throws Exception {
        Product product = productMapper.selectProductById(productId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        if(product==null){
            return null;
        }
        Commodity commodity = commodityMapper.selectCommodityDetailById(product.getCommodityId(),ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        if(commodity==null){
            return null;
        }
        PriceSection priceSection = commodity.getPriceSection();//商品价格段
        Seller seller = commodity.getSeller();//商家
        Map specs = new HashMap();//商品规格Map
        String showLevel="";
        for (CommoditySpec spec : commodity.getCommoditySpecs()) {
            if(spec.getSpec().getSpecName().equals("级别")){
                showLevel = spec.getValue();//级别（页面单独显示）
            }
            specs.put(spec.getSpec().getSpecName(), spec.getValue());//将规格名称和规格内容封装
        }
        Map paymentType=new HashMap();//支付方式
        for (PaymentType pay : PaymentType.values()){
            paymentType.put(pay,pay.toString());
        }
        Favorite favorite=null;
        if(customerId>0){//用户存在则查询商品收藏夹
            favorite=new Favorite();
            favorite.setCustomerId(customerId);
            favorite.setCommodityId(product.getCommodityId());
            favorite=favoriteMapper.findByCustomerAndCommodity(favorite);
        }
        List<Attachment> attachments=attachmentMapper.selectByObjectTypeAndId(ObjectType.COMMODITY.name(),commodity.getCommodityId());//商品图片组
        Set imageSet=new HashSet<>();
        for (Attachment attachment:attachments){
            imageSet.add(attachment.getFilePath());
        }
        //重组数据后清空
        commodity.setBrand(null);
        commodity.setSeller(null);
        commodity.setPriceSection(null);
        commodity.setCommoditySpecs(null);
        CommodityVo commodityVo=new CommodityVo();
        ProductVo productVo=null;
        PriceSectionVo priceSectionVo=null;
        SellerVo sellerVo=null;
        FavoriteVo favoriteVo=null;
        BeanUtils.copyProperties(commodityVo,commodity);
        if(!StringUtils.isEmpty(product)){
            productVo=new ProductVo();
            BeanUtils.copyProperties(productVo,product);
        }
        if(!StringUtils.isEmpty(priceSection)){
            priceSectionVo=new PriceSectionVo();
            BeanUtils.copyProperties(priceSectionVo,priceSection);
        }
        if(!StringUtils.isEmpty(seller)){
            sellerVo=new SellerVo();
            BeanUtils.copyProperties(sellerVo,seller);
        }
        if(!StringUtils.isEmpty(favorite)){
            favoriteVo=new FavoriteVo();
            BeanUtils.copyProperties(favoriteVo,favorite);
        }
        commodityVo.setProductVo(productVo);
        commodityVo.setPriceSectionVo(priceSectionVo);
        commodityVo.setSellerVo(sellerVo);
        commodityVo.setFavoriteVo(favoriteVo);
        commodityVo.setShowLevel(showLevel);
        commodityVo.setSpecs(specs);
        commodityVo.setPaymentType(paymentType);
        commodityVo.setImageSet(imageSet);
        return commodityVo;
    }

    @Override
    public List<Product> getProductsByCommodityId(Integer commodityId,Integer state,Integer publish) {
        List<Product> products = productMapper.selectAllByCommodityId(commodityId,state,publish);
        return products;
    }

    @Override
    public List<Commodity> selectByBrandId(Integer brandId) {
        return commodityMapper.selectByBrandId(brandId);
    }

}
