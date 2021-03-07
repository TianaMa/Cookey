package com.demo.cook.modules.recipe.mapper;

import com.demo.cook.modules.recipe.model.QueryRecipeParams;
import com.demo.cook.modules.recipe.model.RecipeBrief;
import com.demo.cook.modules.recipe.model.Recipe;
import com.demo.cook.modules.recipe.model.RecipeDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {


    int insertSelective(Recipe record) throws Exception;

    int deleteByPrimaryKey(String recipeId) throws Exception;

    int updateByPrimaryKeySelective(Recipe record) throws Exception;

    List<RecipeBrief> queryRecipeList(QueryRecipeParams params) throws Exception;

    RecipeDetails selectDetailsByRecipeId(@Param("recipeId") String recipeId, @Param("loginUserName") String loginUserName) throws Exception;

}