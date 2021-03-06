/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CustomerType;
import com.yunxin.cb.mall.entity.meta.PolicyType;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075  客户
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@ApiModel(value="客户对象",description="客户对象Customer")
public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 3814946735437297136L;

    /**
     * id
     */
    @ApiModelProperty(value="客户ID",name="customerId",example="1")
    private int customerId;
    /**
     * 账户名
     */
    @ApiModelProperty(value="账户名",name="accountName",example="186456789")
    private String accountName;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="password",example="xxxxxx")
    private String password;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="2016-03-22 21:58:43")
    private Date createTime;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名",name="realName",example="张三")
    private String realName;
    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称",name="nickName",example="释然")
    private String nickName;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别",name="sex",example="true")
    private boolean sex = true;
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期",name="birthday",example="2016-03-22 21:58:43")
    private Date birthday;
    /**
     * 用户头像
     */
    @ApiModelProperty(value="用户头像",name="avatarUrl",example="xxx.jpg")
    private String avatarUrl;
    /**
     * 推荐人
     */
    @ApiModelProperty(value="推荐人",name="recommendCustomer",example="")
    private Customer recommendCustomer;
    /**
     * 所在省
     */
    @ApiModelProperty(value="所在省",name="province",example="广东省")
    private String province;
    /**
     * 所在市
     */
    @ApiModelProperty(value="所在市",name="city",example="深圳市")
    private String city;
    /**
     * 所在区
     */
    @ApiModelProperty(value="所在区",name="district",example="福田区")
    private String district;
    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱",name="email",example="4654686465@qq.com")
    private String email;
    /**
     * 手机号码
     * 长度设置为32，方便在用户进行QQ登录时，生成本平台新账号时，用openId(长度32)填充手机号字段，以严格保证mobile唯一。
     */
    @ApiModelProperty(value="手机号码",name="mobile",example="18588888888")
    private String mobile;
    /**
     * 固定电话
     */
    @ApiModelProperty(value="固定电话",name="telephone",example="0755-88888888")
    private String telephone;
    /**
     * 地址
     */
    @ApiModelProperty(value="地址",name="address",example="广东省深圳市福田区平安大厦")
    private String address;
    /**
     * 邮编
     */
    @ApiModelProperty(value="邮编",name="postCode",example="335500")
    private String postCode;
    /**
     * 当前可用积分
     */
    @ApiModelProperty(value="当前可用积分",name="integral",example="5500")
    private int integral;
    /**
     * 总积分
     */
    @ApiModelProperty(value="总积分",name="totalIntegral",example="5500")
    private int totalIntegral;
    /**
     * 已兑换积分
     */
    @ApiModelProperty(value="已兑换积分",name="exchangeIntegral",example="5500")
    private int exchangeIntegral;
    /**
     * 会员卡号
     */
    @ApiModelProperty(value="会员卡号",name="cardNo",example="5500")
    private String cardNo;
    /**
     * 状态：true 启用；false 停用
     */
    @ApiModelProperty(value="状态",name="enabled",example="true")
    private boolean enabled;
    /**
     * 手机是否验证
     */
    @ApiModelProperty(value="手机是否验证",name="mobileChecked",example="true")
    private boolean mobileChecked;
    /**
     * 是否买过保单
     */
    @ApiModelProperty(value="是否买过保单",name="policy",example="true")
    private PolicyType policy;
    /**
     * 邮箱是否验证
     */
    @ApiModelProperty(value="邮箱是否验证",name="emailChecked",example="true")
    private boolean emailChecked;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="remark",example="第三方")
    private String remark;
    /**
     * 等级
     */
    @ApiModelProperty(value="等级",name="rank",example="等级")
    private Rank rank;
    /**
     * 推荐人点赞
     */
    @ApiModelProperty(value="推荐人点赞",name="praise",example="true")
    private boolean praise;
    /**
     * 收到点赞次数
     */
    @ApiModelProperty(value="收到点赞次数",name="praiseNum",example="20")
    private int praiseNum;
    /**
     * 融云token
     */
    @ApiModelProperty(value="融云token",name="rongCloudToken",example="")
    private String rongCloudToken;
    /**
     * openid是唯一对应用户身份的标识，将此ID进行存储便于用户下次登录时辨识其身份，或将其与用户在网站上的原有帐号进行绑定。
     * 以便用户下次登录时可对应到其之前的身份信息，不需要重新授权。
     */
    @ApiModelProperty(value="用户身份的标识",name="qqOpenId",example="")
    private String qqOpenId;
    /**
     * 头像URL
     */
    @ApiModelProperty(value="头像URL",name="qqFigureUrl",example="xxx.jpg")
    private String qqFigureUrl;
    /**
     * 表示当前用户在此网站/应用的登录状态与授权信息
     */
    private String qqAccessToken;

    private int parentCustomerId;
    /**
     * 用户账号类型
     */
    @ApiModelProperty(value="用户账号类型",name="customerType",example="PLATFORM_SELF(\"平台账号\"), QQ(\"qq账号\")")
    private CustomerType customerType;
    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型",name="cardType",example="居民身份证")
    private String cardType;
    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码",name="customerCardNo",example="432522984654655")
    private String customerCardNo;
    /**
     * 证件正面照
     */
    @ApiModelProperty(value="证件正面照",name="cardPositiveImg",example="xxx.jpg")
    private String cardPositiveImg;
    /**
     * 证件反面照
     */
    @ApiModelProperty(value="证件反面照",name="cardNegativeImg",example="xxx.jpg")
    private String cardNegativeImg;
    /**
     * 银行照片
     */
    @ApiModelProperty(value="银行照片",name="bankCardImg",example="xxx.jpg")
    private String bankCardImg;
    /**
     * 等级
     */
    @ApiModelProperty(value="等级",name="customerLevel",example="2")
    private int customerLevel;
    /**
     * 等级编码
     */
    @ApiModelProperty(value="等级编码",name="levelCode",example="22")
    private String levelCode;
    /**
     * 邀请码
     */
    @ApiModelProperty(value="邀请码",name="invitationCode",example="223344")
    private String  invitationCode;
    /**
     * 国籍
     */
    @ApiModelProperty(value="国籍",name="customerCountry",example="中国")
    private String customerCountry;
    /**
     * 证件有效期
     */
    @ApiModelProperty(value="证件有效期",name="customerCardPeroid",example="2018-08-12")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date customerCardPeroid;
    @ApiModelProperty(value="个人信息是否完善",name="isPerfect",example="true")
    private boolean perfect;
    /**
     * 职业类别
     */
    @ApiModelProperty(value="职业类别",name="occupationalCategory",example="教师")
    private String occupationalCategory;

    @ApiModelProperty(value="是否注销",name="cancel",example="true")
    private boolean ynDelete;

    private String token;

    private boolean friend;
    private String state;
    /**
     * 备注名
     */
    private String aliasName;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(unique = true, nullable = false, length = 64)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(length = 64, nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(unique = true, nullable = false,length = 32)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    @Column(unique = true, nullable = false,length = 32)
    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }
    @Column(unique = true, nullable = false,length = 255)
    public String getCardPositiveImg() {
        return cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg;
    }
    @Column(unique = true, nullable = false,length = 255)
    public String getCardNegativeImg() {
        return cardNegativeImg;
    }
    @Column(length = 12, nullable = true)
    public int getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(int customerLevel) {
        this.customerLevel = customerLevel;
    }
    @Column(length = 255)
    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }
    @Column(length = 25)
    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Column(precision = 1)
    public PolicyType getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyType policy) {
        this.policy = policy;
    }

    public void setParentCustomerId(int parentCustomerId) {
        this.parentCustomerId = parentCustomerId;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg;
    }
    @Column(unique = true, nullable = false,length = 255)
    public String getBankCardImg() {
        return bankCardImg;
    }

    public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 64, nullable = true)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(precision = 1)
    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(nullable = true, length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(nullable = true, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = true, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 32, nullable = false, unique = true)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(length = 14, nullable = true)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(length = 255, nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 6, nullable = true)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(length = 12, nullable = true)
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    @Column(length = 12, nullable = true)
    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    @Column(length = 1024, nullable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "RANK")
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Column(length = 12)
    public int getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(int exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    @Column(length = 32)
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(precision = 1)
    public boolean isMobileChecked() {
        return mobileChecked;
    }

    public void setMobileChecked(boolean mobileChecked) {
        this.mobileChecked = mobileChecked;
    }


    @Column(unique = true, length = 32)
    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    @Column(length = 32)
    public String getQqAccessToken() {
        return qqAccessToken;
    }

    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken;
    }

    @Column(length = 32)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(length = 512)
    public String getQqFigureUrl() {
        return qqFigureUrl;
    }

    public void setQqFigureUrl(String qqFigureUrl) {
        this.qqFigureUrl = qqFigureUrl;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Column(precision = 1, columnDefinition = "bit default 0")
    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    @Column(length = 128)
    public String getRongCloudToken() {
        return rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken;
    }

    @Column(length = 256)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECOMMEND_CUSTOMER_ID")
    public Customer getRecommendCustomer() {
        return recommendCustomer;
    }

    public void setRecommendCustomer(Customer recommendCustomer) {
        this.recommendCustomer = recommendCustomer;
    }

    @Column
    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }

    @Column
    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Transient
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Transient
    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
    @Transient
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Transient
    public boolean isPerfect() {
        return perfect;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }

    @Column(length = 60, nullable = true)
    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(length = 20,insertable = true, updatable = true)
    public Date getCustomerCardPeroid() {
        return customerCardPeroid;
    }

    public void setCustomerCardPeroid(Date customerCardPeroid) {
        this.customerCardPeroid = customerCardPeroid;
    }
    @Column(length = 60, nullable = true)
    public String getOccupationalCategory() {
        return occupationalCategory;
    }

    public void setOccupationalCategory(String occupationalCategory) {
        this.occupationalCategory = occupationalCategory;
    }


    @Column(nullable = false, precision = 1)
    public boolean isYnDelete() {
        return ynDelete;
    }

    public void setYnDelete(boolean ynDelete) {
        this.ynDelete = ynDelete;
    }

    @Transient
    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    @Transient
    public Integer getParentCustomerId() {
        if (recommendCustomer == null) {
            return null;
        }
        return recommendCustomer.getCustomerId();
    }
}
