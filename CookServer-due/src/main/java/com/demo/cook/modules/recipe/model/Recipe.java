package com.demo.cook.modules.recipe.model;

import java.util.List;

public class Recipe extends RecipeBase {


    private List<RecipeMaterial> recipeMaterialList;

    private List<RecipeStep> recipeStepList;

    public List<RecipeMaterial> getRecipeMaterialList() {
        return recipeMaterialList;
    }

    public void setRecipeMaterialList(List<RecipeMaterial> recipeMaterialList) {
        this.recipeMaterialList = recipeMaterialList;
    }

    public List<RecipeStep> getRecipeStepList() {
        return recipeStepList;
    }

    public void setRecipeStepList(List<RecipeStep> recipeStepList) {
        this.recipeStepList = recipeStepList;
    }


    public Recipe() {
        super();
    }


}