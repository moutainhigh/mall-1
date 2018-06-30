package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Recipe;
import com.yunxin.cb.mall.entity.RecipeStep;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxing on 2016/2/25.
 */
public interface IRecipeService {
    Page<Recipe> pageRecipes(PageSpecification<Recipe> query, int customerId);

    Recipe addRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    Recipe getRecipeById(int recipeId);

    List<Recipe> getRecipesByCustomerId(int customerId);

    void removeRecipeById(int recipeId);

    void updateRecipeImageByRecipeId(int recipeId, Map<String, String> newImagePaths);

    List<RecipeStep> getRecipeStepsByRecipeId(int recipeId);
}
