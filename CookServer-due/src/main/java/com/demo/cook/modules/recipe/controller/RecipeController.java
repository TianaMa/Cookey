package com.demo.cook.modules.recipe.controller;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.recipe.model.QueryRecipeParams;
import com.demo.cook.modules.recipe.model.Recipe;
import com.demo.cook.modules.recipe.model.RecipeBrief;
import com.demo.cook.modules.recipe.model.RecipeDetails;
import com.demo.cook.modules.recipe.service.IRecipeService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {


    private IRecipeService recipeService;

    @Autowired
    public void setRecipeService(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public RtnResult publish(@RequestBody Recipe recipe)  {
        try {
            System.out.println("publish==========");
            return recipeService.publishRecipe(recipe);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/updateMyRecipe",method = RequestMethod.POST)
    public RtnResult updateMyRecipe(@RequestBody Recipe recipe)  {
        try {
            System.out.println("updateMyRecipe==========");
            return recipeService.updateMyRecipe(recipe);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/queryRecipeList",method = RequestMethod.POST)
    public RtnResult<PageInfo<RecipeBrief>> queryRecipeList(@RequestBody QueryRecipeParams params)  {
        try {
            System.out.println(new Gson().toJson(params));
            return recipeService.queryRecipeList(params);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/queryRecipeDetails",method = RequestMethod.GET)
    public RtnResult<RecipeDetails> queryRecipeDetails(HttpServletRequest params)  {
        try {
            return recipeService.queryRecipeDetails(params.getParameter("recipeId"),params.getParameter("loginUserName"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }


}
