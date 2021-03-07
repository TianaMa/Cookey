package com.demo.cook.modules.recipe.mapper;

import com.demo.cook.modules.recipe.model.RecipeStep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RecipeStepMapper {

    //批量插入 某菜谱的步骤
    int batchInsertStep(@Param("recipeId") String recipeId,@Param("stepList") List<RecipeStep> stepList) throws Exception;

    //删除 某菜谱的步骤
    int deleteStepByRecipeId(String recipeId) throws Exception;

    //查询 某菜谱的步骤
    List<RecipeStep> selectStepListByRecipeId(String recipeId) throws Exception;

}