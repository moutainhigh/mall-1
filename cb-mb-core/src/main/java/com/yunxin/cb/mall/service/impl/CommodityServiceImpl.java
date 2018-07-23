package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.CommoditySpec;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.mapper.AttributeGroupMapper;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.mapper.FavoriteMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.CommodityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private AttributeGroupMapper attributeGroupMapper;

    @Override
    public Commodity selectByPrimaryKey(int commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    @Override
    public Map getCommdityDetail(int productId,int customerId) {
        Map resultMap = new HashMap();
        Product product = productMapper.selectProductById(productId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());
        Commodity commodity = commodityMapper.selectCommodityDetailById(product.getCommodityId());
        resultMap.put("product",product);//货品
        resultMap.put("brand", commodity.getBrand());//品牌
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
        if(customerId>0){//用户存在则查询商品收藏夹
            Favorite favorite=new Favorite();
            favorite.setCustomerId(customerId);
            favorite.setCommodityId(product.getCommodityId());
            favorite=favoriteMapper.findByCustomerAndCommodity(favorite);
            resultMap.put("favorite",favorite);//收藏夹
        }
        //重组数据后清空
        commodity.setBrand(null);
        commodity.setSeller(null);
        commodity.setPriceSection(null);
        commodity.setCommoditySpecs(null);
        resultMap.put("commodity",commodity);//商品
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

    /**
     * @title: 获取原来的图
     * @param: [imagesDir, commodity]
     * @return: java.lang.String[]
     * @auther: eleven
     * @date: 2018/7/20 10:15
     */
    private String[] getImagePath(String imagesDir, Commodity commodity) {

        File imageDir = new File(imagesDir + commodity.getCommodityCode());
        String[] images = imageDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("jpg")) {
                    return true;
                }
                return false;
            }
        });
        return images;
    }
}
