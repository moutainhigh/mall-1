package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CuisineType;
import com.yunxin.cb.mall.entity.meta.ProductionDifficultyType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by chenxing on 2016/2/24.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Recipe {

    private int recipeId;

    /**
     * 会员
     */
    private Customer customer;

    private String recipeCode;
    /**
     * 菜品名称
     */
    private String recipeName;

    /**
     * 菜系
     */
    private CuisineType cuisineType;

    /**
     * 制作难度
     */
    private ProductionDifficultyType productionDifficultyType;

    /**
     * 菜品口味
     */
    private String taste;

    /**
     * 烹调方式
     */
    private String cookingMethod;

    /**
     * 所需时间(分钟)
     */
    private int timeNeeded;

    /**
     * 佐料 调味料
     */
    private String condiment;

    /**
     * 食材明细
     */
    private String foodMaterialDetails;

    /**
     * 详细步骤
     */
    private String detailedSteps;

    /**
     * 小贴士
     */
    private String tips;

    /**
     * 相关人群
     */
    private String relatedGroups;

    /**
     * 注意事项
     */
    private String precautions;

    private String[] stepName;

    private String[] stepDescription;

    private String[] stepPicPath;

    private int[] stepWhen;

    private int[] stepOrder;

    private String[] stepRemark;

    private List<RecipeStep> recipeSteps = new ArrayList<>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Column(length = 32)
    public String getRecipeCode() {
        return recipeCode;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    @Column(length = 64)
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    @Column(length = 32)
    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Column(length = 64)
    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ProductionDifficultyType getProductionDifficultyType() {
        return productionDifficultyType;
    }

    public void setProductionDifficultyType(ProductionDifficultyType productionDifficultyType) {
        this.productionDifficultyType = productionDifficultyType;
    }

    @Column(length = 3)
    public int getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    @Column(length = 512)
    public String getCondiment() {
        return condiment;
    }

    public void setCondiment(String condiment) {
        this.condiment = condiment;
    }

    @Column(length = 512)
    public String getFoodMaterialDetails() {
        return foodMaterialDetails;
    }

    public void setFoodMaterialDetails(String foodMaterialDetails) {
        this.foodMaterialDetails = foodMaterialDetails;
    }

    @Column(length = 2000)
    public String getDetailedSteps() {
        return detailedSteps;
    }

    public void setDetailedSteps(String detailedSteps) {
        this.detailedSteps = detailedSteps;
    }

    @Column(length = 512)
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Column(length = 512)
    public String getRelatedGroups() {
        return relatedGroups;
    }

    public void setRelatedGroups(String relatedGroups) {
        this.relatedGroups = relatedGroups;
    }

    @Column(length = 512)
    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.REMOVE)
    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    @Transient
    public String[] getStepName() {
        return stepName;
    }

    public void setStepName(String[] stepName) {
        this.stepName = stepName;
    }

    @Transient
    public String[] getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String[] stepDescription) {
        this.stepDescription = stepDescription;
    }

    @Transient
    public String[] getStepPicPath() {
        return stepPicPath;
    }

    public void setStepPicPath(String[] stepPicPath) {
        this.stepPicPath = stepPicPath;
    }

    @Transient
    public int[] getStepWhen() {
        return stepWhen;
    }

    public void setStepWhen(int[] stepWhen) {
        this.stepWhen = stepWhen;
    }

    @Transient
    public int[] getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int[] stepOrder) {
        this.stepOrder = stepOrder;
    }

    @Transient
    public String[] getStepRemark() {
        return stepRemark;
    }

    public void setStepRemark(String[] stepRemark) {
        this.stepRemark = stepRemark;
    }
}
