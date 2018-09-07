package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunxin.cb.mall.entity.Attribute;
import com.yunxin.cb.mall.entity.AttributeGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @title: 属性组VO
 * @auther: eleven
 * @date: 2018/7/28 17:24
 */
@ApiModel(value="属性组VO",description="属性组VO对象 AttributeGroup")
public class AttributeGroupVO implements java.io.Serializable{

    private static final long serialVersionUID = 3510680792117822734L;

    /** 属性组id */
    @ApiModelProperty(value="属性组ID",name="groupId",example="1")
    private Integer groupId;

    /** 创建时间 */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-28 17:27:13")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 属性组名称 */
    @ApiModelProperty(value="属性组名称",name="groupName",example="颜色")
    private String groupName;

    @ApiModelProperty(value="属性列表",name="attributes",example="AttributeVO")
    private Set<AttributeVO> attributes = new HashSet<>();

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<AttributeVO> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AttributeVO> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "AttributeGroupVO{" +
                "groupId=" + groupId +
                ", createTime=" + createTime +
                ", groupName='" + groupName + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeGroupVO that = (AttributeGroupVO) o;

        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        return groupId;
    }

    /**
     * @title: 实体转VO
     * @param: []
     * @return: void
     * @auther: eleven
     * @date: 2018/7/28 17:31
     */
    public static List<AttributeGroupVO> convertVO(List<AttributeGroup> groups){
        List<AttributeGroupVO> groupVOs= new ArrayList<>();
        try {
            if(groups!=null&&groups.size()>0){
                for(AttributeGroup group:groups){
                    AttributeGroupVO groupVO=new AttributeGroupVO();
                    BeanUtils.copyProperties(groupVO,group);
                    Set<AttributeVO> attributeVOs = new HashSet<>();
                    if(group.getAttributes()!=null&&group.getAttributes().size()>0) {
                        for (Attribute attr : group.getAttributes()) {
                            AttributeVO attrVO=new AttributeVO();
                            BeanUtils.copyProperties(attrVO,attr);
                            attributeVOs.add(attrVO);
                        }
                        groupVO.setAttributes(attributeVOs);
                    }
                    groupVOs.add(groupVO);
                }
                //根据groupId排序
                Collections.sort(groupVOs, new Comparator<AttributeGroupVO>() {
                    @Override
                    public int compare(AttributeGroupVO o1, AttributeGroupVO o2) {
                        if( o1.getGroupId()>o2.getGroupId()){
                            return 1;
                        }else if( o1.getGroupId()<o2.getGroupId()){
                            return -1;
                        }else{
                            return 0;
                        }
                    }
                });
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return groupVOs;
    }
}
