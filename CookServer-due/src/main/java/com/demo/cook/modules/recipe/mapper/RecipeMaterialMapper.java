package com.demo.cook.modules.recipe.mapper;

import com.demo.cook.modules.recipe.model.RecipeMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RecipeMaterialMapper {


    //批量插入食材信息
    int batchInsertMaterial(@Param("recipeId") String recipeId, @Param("materialList") List<RecipeMaterial> materialList) throws Exception;

    //删除某菜谱的食材信息
    int deleteByRecipeId(String recipeId) throws Exception;

    //查询某菜谱的步骤
    List<RecipeMaterial> selectMaterialListByRecipeId(String recipeId) throws Exception;

}