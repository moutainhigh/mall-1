/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author z001075  商品
 */

public class Commodity implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = -3113613325145218113L;

    /**
     * 商品ID
     */
    private int commodityId;
    /**
     * 商品分类
     */
    private Catalog catalog;


    /**
     * 商品编码
     */
    private String commodityCode;
    /**
     * 商品名
     */
    private String commodityName;
    /**
     * 商品拼音名
     */
    private String commodityPYName;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 商品标题
     */
    private String commodityTitle;
    /**
     * 成本价
     */
    private double costPrice;
    /**
     * 销售价
     */
    private double sellPrice;
    /**
     * 市场价格
     */
    private double marketPrice;

    /**
     * 产地省份
     */
    private String province;
    /**
     * 产地市区
     */
    private String city;
    /**
     * 默认图片路径
     */
    private String defaultPicPath;
    /**
     * seo关键字
     */
    private String seoKey;
    /**
     * seo标题
     */
    private String seoTitle;
    /**
     * seo描述
     */
    private String seoDescription;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否为热门商品
     */
    private boolean popular;
    /**
     * 是否为特惠商品
     */
    private boolean special;
    /**
     * 是否为推荐商品
     */
    private boolean recommend;
    /**
     * 是否为赠品 false为非赠品 true为赠品
     */
    private boolean giveaway;
    /**
     * 是否可换购 false为非换购品 true为换购品
     */
    private boolean barter;
    /**
     * 是否可预售 false为不可预售 true为可预售
     */
    private boolean preSell;
    /**
     * 是否禁止空运
     */
    private boolean forbidAirCargo;
    /**
     * 商品介绍
     */
    private String content;
    /**
     * 销量
     */
    private int saleNum;
    /**
     * 包装清单
     ***/
    private String packingList;

    /**
     * 重量
     */
    private double weight;
    /**
     * 体积
     */
    private double volume;



    /******************form 字段*****************/
    private int[] specId;

    private String[] specValue;

    /**
     * 图片字段
     */
    private String[] imagePath;
    /***
     * 审核备注
     */
    private String auditRemark;


    public Commodity() {
    }

    public Commodity(int commodityId) {
        this.commodityId = commodityId;
    }


}
