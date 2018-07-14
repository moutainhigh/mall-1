/*
 * Powered By [spring-boot-framework]
 * Since 2015 - 2018
 */


package com.yunxin.cb.console.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
*  *
 * @author gonglei
 * @version 2.0
 * @since 2.0
 *
* 
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Resource implements Serializable {

    private static final long serialVersionUID = 5454155825314635342L;


    //columns START
    /**
    * 资源ID
    */
    private int rescId;

    /**
    * 资源编码
    */
    private String rescCode;

    /**
    * 资源名字
    */
    private String rescName;



    //columns END


	public Resource(){
	}

	public Resource(
		int rescId
	){
		this.rescId = rescId;
	}


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, length = 10)
    public int getRescId() {
        return this.rescId;
    }

    public void setRescId(int rescId) {
        this.rescId = rescId;
    }

    @Column(unique = true, nullable = false,  length = 32)
    public String getRescCode() {
        return this.rescCode;
    }

    public void setRescCode(String rescCode) {
        this.rescCode = rescCode;
    }

    @Column(unique = false, nullable = false,  length = 32)
    public String getRescName() {
        return this.rescName;
    }

    public void setRescName(String rescName) {
        this.rescName = rescName;
    }



}