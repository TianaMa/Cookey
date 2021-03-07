package com.demo.cook.modules.recipe.service;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.recipe.mapper.RecipeMapper;
import com.demo.cook.modules.recipe.mapper.RecipeMaterialMapper;
import com.demo.cook.modules.recipe.mapper.RecipeStepMapper;
import com.demo.cook.modules.recipe.model.QueryRecipeParams;
import com.demo.cook.modules.recipe.model.RecipeBrief;
import com.demo.cook.modules.recipe.model.Recipe;
import com.demo.cook.modules.recipe.model.RecipeDetails;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements IRecipeService{

    private RecipeMapper recipeMapper;
    private RecipeMaterialMapper recipeMaterialMapper;
    private RecipeStepMapper recipeStepMapper;

    @Autowired
    public void setRecipeMapper(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }
    @Autowired
    public void setRecipeMaterialMapper(RecipeMaterialMapper recipeMaterialMapper) {
        this.recipeMaterialMapper = recipeMaterialMapper;
    }
    @Autowired
    public void setRecipeStepMapper(RecipeStepMapper recipeStepMapper) {
        this.recipeStepMapper = recipeStepMapper;
    }

    @Override
    public RtnResult<Recipe> publishRecipe(Recipe recipe) throws Exception {

        String recipeId = UUID.randomUUID().toString();
        recipe.setRecipeId(recipeId);

        recipeMapper.insertSelective(recipe);

        recipeMaterialMapper.batchInsertMaterial(recipeId,recipe.getRecipeMaterialList());

        recipeStepMapper.batchInsertStep(recipeId,recipe.getRecipeStepList());

        return new RtnResult<>(Rtn.success);
    }



    @Override
    public RtnResult updateMyRecipe(Recipe recipe) throws Exception {

        recipeMapper.updateByPrimaryKeySelective(recipe);

        recipeMaterialMapper.deleteByRecipeId(recipe.getRecipeId());
        recipeStepMapper.deleteStepByRecipeId(recipe.getRecipeId());

        recipeMaterialMapper.batchInsertMaterial(recipe.getRecipeId(),recipe.getRecipeMaterialList());
        recipeStepMapper.batchInsertStep(recipe.getRecipeId(),recipe.getRecipeStepList());

        return new RtnResult<>(Rtn.success);
    }


    @Override
    public RtnResult<PageInfo<RecipeBrief>> queryRecipeList(QueryRecipeParams params) throws Exception {

        PageHelper.startPage(params.getPageNum(), params.getPageSize(),true);
        List<RecipeBrief> recipeBriefs = recipeMapper.queryRecipeList(params);
        PageInfo<RecipeBrief> pageInfo = new PageInfo(recipeBriefs);

        return new RtnResult(Rtn.success,pageInfo);
    }

    @Override
    public RtnResult<RecipeDetails> queryRecipeDetails(String recipeId,String loginUserName) throws Exception {
        if(StringUtils.isNullOrEmpty(recipeId)){
            return new RtnResult<>(Rtn.missingParameter);
        }

        RecipeDetails recipe = recipeMapper.selectDetailsByRecipeId(recipeId,loginUserName);
        if (recipe==null){
            return new RtnResult<>(Rtn.noData);
        }
        recipe.setRecipeMaterialList(recipeMaterialMapper.selectMaterialListByRecipeId(recipeId));
        recipe.setRecipeStepList(recipeStepMapper.selectStepListByRecipeId(recipeId));

        return new RtnResult<>(Rtn.success,recipe);
    }


}
