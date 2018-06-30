/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author qulei
 * 投诉
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Complaint implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 投诉建议ID
     */

    private int complaintId;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 标题
     */

    private String title;

    /**
     * 内容
     */

    private String content;

    /**
     * 客户
     */

    private Customer customer;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int cpId) {
        this.complaintId = cpId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, length = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = true, length = 512)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
