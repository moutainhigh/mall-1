package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecipeStepDao extends JpaRepository<RecipeStep, Integer>, JpaSpecificationExecutor<RecipeStep> {

    @Modifying
    @Query("delete from RecipeStep setp where setp.recipe.recipeId =?1")
    void deleteByRecipeId(int recipeId);

    @Modifying
    @Query("update RecipeStep setp set setp.picPath=?2 where setp.stepId =?1")
    void updateImgsBySetpId(int stepId, String picPath);
}
