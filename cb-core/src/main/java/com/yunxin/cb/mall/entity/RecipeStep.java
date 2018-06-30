package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class RecipeStep {

    private int stepId;

    private Recipe recipe;

    private String stepName;

    private String description;

    private String picPath;

    private int stepWhen;

    private int stepOrder;

    private String remark;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    @Column(nullable = false, length = 64)
    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Column(nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(precision = 4)
    public int getStepWhen() {
        return stepWhen;
    }

    public void setStepWhen(int stepWhen) {
        this.stepWhen = stepWhen;
    }

    @Column(precision = 4)
    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
