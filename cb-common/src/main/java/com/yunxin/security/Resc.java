/**
 *
 */
package com.yunxin.security;

/**
 * @author keymean
 */
public class Resc {

    private String rescCode;

    private String name;

    private String type;

    private String rescValue;

    public Resc(String rescCode, String name, String type, String rescValue) {
        super();
        this.rescCode = rescCode;
        this.name = name;
        this.type = type;
        this.rescValue = rescValue;
    }

    public Resc() {

    }

    public String getRescCode() {
        return rescCode;
    }

    public void setRescCode(String rescId) {
        this.rescCode = rescId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRescValue() {
        return rescValue;
    }

    public void setRescValue(String url) {
        this.rescValue = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
