package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chenxing on 2016/2/25.
 */
public interface RecipeDao extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {
    @Query("select re from Recipe re where re.customer.customerId =?1 ")
    List<Recipe> findByCustomerId(int customerId);

    @Query("select r from Recipe r left join fetch r.recipeSteps where r.recipeId=:recipeId")
    Recipe getRecipeById(@Param("recipeId") int recipeId);
}
