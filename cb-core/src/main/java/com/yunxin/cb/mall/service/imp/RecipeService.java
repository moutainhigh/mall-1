package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.RecipeDao;
import com.yunxin.cb.mall.dao.RecipeStepDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IRecipeService;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxing on 2016/2/25.
 */
@Service
@Transactional
public class RecipeService implements IRecipeService {

    @Resource
    private RecipeDao recipeDao;

    @Resource
    private RecipeStepDao recipeStepDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Recipe> pageRecipes(final PageSpecification<Recipe> query, int customerId) {
        query.setCustomSpecification(new CustomSpecification<Recipe>() {
            @Override
            public void addConditions(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                predicates.add(builder.equal(root.get(Recipe_.customer).get(Customer_.customerId), customerId));
            }
        });
        Page<Recipe> page = recipeDao.findAll(query, query.getPageRequest());
        return page;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipe.setRecipeCode(UUIDGeneratorUtil.getUUCode());
        Recipe dbRecipe = recipeDao.save(recipe);
        String[] stepName = recipe.getStepName();
        String[] stepDescription = recipe.getStepDescription();
        String[] stepRemark = recipe.getStepRemark();
        String[] imagePath = recipe.getStepPicPath();
        int[] stepWhen = recipe.getStepWhen();
        int[] sortOrder = recipe.getStepOrder();
        for (int i = 0; i < stepName.length; i++) {
            RecipeStep recipeStep = new RecipeStep();
            recipeStep.setStepName(stepName[i]);
            recipeStep.setDescription(stepDescription[i]);
            recipeStep.setRemark(stepRemark[i]);
            recipeStep.setPicPath(imagePath[i]);
            recipeStep.setStepWhen(stepWhen[i]);
            recipeStep.setStepOrder(sortOrder[i]);
            recipeStep.setRecipe(dbRecipe);
            recipeStepDao.save(recipeStep);
        }
        return dbRecipe;
    }

    @Override
    public void updateRecipeImageByRecipeId(int recipeId, Map<String, String> newImagePaths) {
        List<RecipeStep> recipeSteps = getRecipeStepsByRecipeId(recipeId);
        if (LogicUtils.isNotNullAndEmpty(recipeSteps) && LogicUtils.isNotNullAndEmpty(newImagePaths)) {
            for (RecipeStep recipeStep : recipeSteps) {
                Iterator i = newImagePaths.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry k = (Map.Entry) i.next();
                    if (recipeStep.getPicPath().equals(k.getKey())) {
                        recipeStepDao.updateImgsBySetpId(recipeStep.getStepId(), String.valueOf(k.getValue()));
                    }
                }

            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RecipeStep> getRecipeStepsByRecipeId(int recipeId) {
        return recipeStepDao.findAll(new Specification<RecipeStep>() {
            @Override
            public Predicate toPredicate(Root<RecipeStep> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get(RecipeStep_.recipe).get(Recipe_.recipeId), recipeId));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(RecipeStep_.stepOrder)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        Recipe recipeDB = recipeDao.findOne(recipe.getRecipeId());
        AttributeReplication.copying(recipe, recipeDB, Recipe_.recipeName, Recipe_.cuisineType, Recipe_.productionDifficultyType, Recipe_.taste,
                Recipe_.cookingMethod, Recipe_.timeNeeded, Recipe_.foodMaterialDetails, Recipe_.detailedSteps,
                Recipe_.tips, Recipe_.relatedGroups, Recipe_.precautions);
        recipeStepDao.deleteByRecipeId(recipeDB.getRecipeId());
        String[] stepName = recipe.getStepName();
        String[] stepDescription = recipe.getStepDescription();
        String[] stepRemark = recipe.getStepRemark();
        String[] imagePath = recipe.getStepPicPath();
        int[] stepWhen = recipe.getStepWhen();
        int[] sortOrder = recipe.getStepOrder();
        for (int i = 0; i < stepName.length; i++) {
            RecipeStep recipeStep = new RecipeStep();
            recipeStep.setStepName(stepName[i]);
            recipeStep.setDescription(stepDescription[i]);
            recipeStep.setRemark(stepRemark[i]);
            recipeStep.setPicPath(imagePath[i]);
            recipeStep.setStepWhen(stepWhen[i]);
            recipeStep.setStepOrder(sortOrder[i]);
            recipeStep.setRecipe(recipeDB);
            recipeStepDao.save(recipeStep);
        }
        return recipeDB;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Recipe getRecipeById(int recipeId) {
        return recipeDao.getRecipeById(recipeId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Recipe> getRecipesByCustomerId(int customerId) {
        return recipeDao.findByCustomerId(customerId);
    }

    @Override
    public void removeRecipeById(int recipeId) {
        recipeDao.delete(recipeId);
    }
}
