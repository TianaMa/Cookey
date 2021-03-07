package com.demo.cook.modules.recipe.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.recipe.model.QueryRecipeParams;
import com.demo.cook.modules.recipe.model.RecipeBrief;
import com.demo.cook.modules.recipe.model.Recipe;
import com.demo.cook.modules.recipe.model.RecipeDetails;
import com.github.pagehelper.PageInfo;


public interface IRecipeService {

    RtnResult<Recipe> publishRecipe(Recipe recipe) throws Exception;


    RtnResult updateMyRecipe(Recipe recipe) throws Exception;

    RtnResult<PageInfo<RecipeBrief>> queryRecipeList(QueryRecipeParams params) throws Exception;


    RtnResult<RecipeDetails> queryRecipeDetails(String recipeId,String loginUserName) throws Exception;

}
