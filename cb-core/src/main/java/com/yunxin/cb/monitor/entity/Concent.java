/**
 *
 */
package com.yunxin.cb.monitor.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.PadState;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Aidy
 * PAD设备
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Concent implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 882018236089715944L;

    /**
     * ID
     */
    private int concentId;

    /**
     * 所属用户
     */
    private Customer customer;
    /**
     * 编码,唯一标识每台平板
     */
    private String concentCode;

    /**
     * 名称
     */
    private String concentName;


    private PadState padState;
    /**
     * 在线
     */
    private boolean online;


    /**
     * 备注
     */
    private String remark;

    private List<Device> devices = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getConcentId() {
        return concentId;
    }

    public void setConcentId(int equipmentId) {
        this.concentId = equipmentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer exhibitionHall) {
        this.customer = exhibitionHall;
    }

    @Column(nullable = false, length = 32)
    public String getConcentCode() {
        return concentCode;
    }

    public void setConcentCode(String equipmentCode) {
        this.concentCode = equipmentCode;
    }

    @Column(nullable = false, length = 32)
    public String getConcentName() {
        return concentName;
    }

    public void setConcentName(String equipmentName) {
        this.concentName = equipmentName;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PadState getPadState() {
        return padState;
    }

    public void setPadState(PadState padState) {
        this.padState = padState;
    }

    @Column(nullable = false, precision = 1)
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "concent", cascade = CascadeType.REMOVE)
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
