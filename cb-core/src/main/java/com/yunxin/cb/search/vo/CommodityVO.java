package com.yunxin.cb.search.vo;

import com.yunxin.cb.mall.entity.Commodity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ApiModel(value="商品搜索对象",description="商品搜索对象 CommodityVO")
public class CommodityVO implements java.io.Serializable {
    private static final long serialVersionUID = -3968560903203859821L;

    public CommodityVO() {
    }

    public CommodityVO(Commodity commodity) {
        try{
            this.commodityId = commodity.getCommodityId();
            this.commodityName = commodity.getCommodityName();
            this.commodityCode = commodity.getCommodityCode();
            this.commodityTitle = commodity.getCommodityTitle();
            this.commodityPYName = commodity.getCommodityPYName();
            this.description = commodity.getDescription();
            this.brand = new Brand(commodity.getBrand());
            this.province = commodity.getProvince();
            this.city = commodity.getCity();
            this.marketPrice = commodity.getMarketPrice();
            this.sellPrice = commodity.getSellPrice();
            this.seller = new Seller(commodity.getSeller());
            this.saleNum = commodity.getSaleNum();
            this.popular = commodity.isPopular();
            this.priceSection = new PriceSection(commodity.getPriceSection().getStartPrice(), commodity.getPriceSection().getEndPrice());
            this.recommend = commodity.isRecommend();
            if(commodity.getDefaultProduct()!=null){
                this.defaultProduct = commodity.getDefaultProduct().getProductId();
                this.sellPrice = commodity.getDefaultProduct().getSalePrice();//EL里面商品销售价为默认货品价
            }
            this.special = commodity.isSpecial();
            //商品分类
            commodity.getCommodityCategories().forEach(commodityCategory -> {
                Category category = new Category();
                try {
                    BeanUtils.copyProperties(category, commodityCategory.getCategory());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.categories.add(category);
            });
            //商品规格
            commodity.getCommoditySpecs().forEach(commoditySpec -> {
                CommoditySpec spec = new CommoditySpec(commoditySpec.getSpec().getSpecName(), commoditySpec.getValue());
                this.commoditySpecs.add(spec);
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 商品ID
     */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private int commodityId;

    /**
     * 商品所属价格段
     */
    @ApiModelProperty(value="价格段",name="priceSection",example="1000-19999")
    private PriceSection priceSection;
    /**
     * 品牌
     */
    @ApiModelProperty(value="品牌",name="brand",example="品牌")
    private Brand brand;
    /**
     * 供应商
     */
    @ApiModelProperty(value="商家",name="seller",example="商家")
    private Seller seller;
    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码",name="commodityCode",example="121233485")
    private String commodityCode;
    /**
     * 商品名
     */
    @ApiModelProperty(value="商品名",name="commodityName",example="BMWX1")
    private String commodityName;
    /**
     * 商品拼音名
     */
    @ApiModelProperty(value="商品拼音名",name="commodityPYName",example="BMWX")
    private String commodityPYName;
    /**
     * 简称
     */
    @ApiModelProperty(value="简称",name="shortName",example="BMW")
    private String shortName;
    /**
     * 商品标题
     */
    @ApiModelProperty(value="商品标题",name="commodityTitle",example="BMWX1")
    private String commodityTitle;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述",name="description",example="1")
    private String description;
    /**
     * 销售价
     */
    @ApiModelProperty(value="销售价",name="sellPrice",example="239888")
    private double sellPrice;
    /**
     * 市场价格
     */
    @ApiModelProperty(value="市场价格",name="marketPrice",example="239888")
    private double marketPrice;
    /**
     * 产地省份
     */
    @ApiModelProperty(value="产地省份",name="province",example="广东省")
    private String province;
    /**
     * 产地市区
     */
    @ApiModelProperty(value="产地市区",name="city",example="深圳")
    private String city;
    /**
     * 默认图片路径
     */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw.jpg")
    private String defaultPicPath;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="bmw.jpg")
    private Date createTime;
    /**
     * 是否为热门商品
     */
    @ApiModelProperty(value="是否为热门商品",name="popular",example="true")
    private boolean popular;
    /**
     * 是否为特惠商品
     */
    @ApiModelProperty(value="是否为特惠商品",name="special",example="true")
    private boolean special;
    /**
     * 是否为推荐商品
     */
    @ApiModelProperty(value="是否为推荐商品",name="recommend",example="true")
    private boolean recommend;
    /**
     * 销量
     */
    @ApiModelProperty(value="销量",name="saleNum",example="1000")
    private int saleNum;
    /**
     * 商品分类
     */
    @ApiModelProperty(value="商品分类",name="categories",example="分类")
    private Set<Category> categories = new HashSet<>();

    /**
     * 商品规格
     */
    @ApiModelProperty(value="商品规格",name="commoditySpecs",example="商品规格")
    private Set<CommoditySpec> commoditySpecs = new HashSet<>();
    /**
     * 默认货品id
     */
    @ApiModelProperty(value="默认货品id",name="defaultProduct",example="11")
    private int defaultProduct;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityPYName() {
        return commodityPYName;
    }

    public void setCommodityPYName(String commodityPYName) {
        this.commodityPYName = commodityPYName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(Set<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public int getDefaultProduct() {
        return defaultProduct;
    }

    public void setDefaultProduct(int defaultProduct) {
        this.defaultProduct = defaultProduct;
    }
}
