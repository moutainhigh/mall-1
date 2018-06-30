package com.yunxin.cb.mall.vo;

import java.util.List;

public class ProductPropertyVo {
    public int propertyGroupId;

    public String propertyGroupName;

    public int propertyId;

    public String propertyName;

    public List<PropValue> propValueList;

    public int getPropertyGroupId() {
        return propertyGroupId;
    }


    public void setPropertyGroupId(int propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }


    public String getPropertyGroupName() {
        return propertyGroupName;
    }


    public void setPropertyGroupName(String propertyGroupName) {
        this.propertyGroupName = propertyGroupName;
    }

    public int getPropertyId() {
        return propertyId;
    }


    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }


    public String getPropertyName() {
        return propertyName;
    }


    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    public List<PropValue> getPropValueList() {
        return propValueList;
    }


    public void setPropValueList(List<PropValue> propValueList) {
        this.propValueList = propValueList;
    }


    public class PropValue {
        public String name;

        public int id;

        public String propPic;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPropPic() {
            return propPic;
        }

        public void setPropPic(String propPic) {
            this.propPic = propPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
