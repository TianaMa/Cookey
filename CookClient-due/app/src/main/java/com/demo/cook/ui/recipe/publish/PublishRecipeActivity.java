package com.demo.cook.ui.recipe.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.demo.basecode.ui.ToastyUtils;
import com.demo.baselib.adapter.CmnRcvAdapter;
import com.demo.baselib.design.BaseActivity;
import com.demo.baselib.view.RcvDragVerticalCallback;
import com.demo.cook.R;
import com.demo.cook.base.event.BusEvent;
import com.demo.cook.base.http.QiNiuUtil;
import com.demo.cook.databinding.ActivityPublishRecipeBinding;
import com.demo.cook.databinding.ItemLayoutRecipeMaterialBinding;
import com.demo.cook.databinding.ItemLayoutRecipeStepBinding;
import com.demo.cook.ui.classes.model.data.RecipeSort;
import com.demo.cook.ui.recipe.model.data.RecipeMaterial;
import com.demo.cook.ui.recipe.model.data.RecipeStep;
import com.demo.cook.utils.upload.UpLoadUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class PublishRecipeActivity extends BaseActivity<ActivityPublishRecipeBinding, PublishRecipeViewModel> {

    private static final String RECIPE_ID = "recipeId";
    private static final String RECIPE_NAME = "recipeName";

    public static void actionCreate(Context context,String recipeName){
        Intent intent = new Intent(context,PublishRecipeActivity.class);
        intent.putExtra(RECIPE_NAME,recipeName);
        context.startActivity(intent);
    }

    public static void actionEdit(Context context,String recipeId){
        Intent intent = new Intent(context,PublishRecipeActivity.class);
        intent.putExtra(RECIPE_ID,recipeId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_publish_recipe;
    }

    @Override
    protected PublishRecipeViewModel getViewModel() {
        return new ViewModelProvider(this).get(PublishRecipeViewModel.class);
    }

    ItemTouchHelper materialItemTouchHelper;
    ItemTouchHelper stepItemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding.setMViewModel(mViewModel);


        try {
            List<RecipeSort> recipeSortList = new Gson().fromJson(
                    new InputStreamReader(this.getAssets().open("recipeSortMenu.json"), "UTF-8"),
                    new TypeToken<List<RecipeSort>>() {}.getType()
            );

            mViewModel.recipe.observe(this, recipe -> {
                for (RecipeSort sort:recipeSortList){
                    if(sort.getSortId().equals(recipe.getSortId())){
                        mDataBinding.tvPublishRecipeSortName.setText(sort.getSortName());break;
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }



        String recipeId = getIntent().getStringExtra(RECIPE_ID);
        if(TextUtils.isEmpty(recipeId)){
            mViewModel.initData();
            mViewModel.recipe.getValue().setRecipeName(getIntent().getStringExtra(RECIPE_NAME));
        }else {
            mViewModel.getRecipeDetails(recipeId);
        }

        //封面选择
        mDataBinding.flPublishRecipeCover.setOnClickListener(v ->
                UpLoadUtils.upLoadSingleImage(
                        PublishRecipeActivity.this,
                        QiNiuUtil.Prefix.IMAGE_RECIPE_COVER,
                        path -> mViewModel.recipe.getValue().setCover(path.get(0))
                )
        );

        //食材adapter 及拖动
        mDataBinding.rcvPublishRecipeMaterial.setAdapter(new CmnRcvAdapter<RecipeMaterial>(
                this, R.layout.item_layout_recipe_material,
                mViewModel.recipeMaterialListData
        ) {
            @Override
            public void convert(CmnViewHolder holder, RecipeMaterial recipeMaterial, int position) {
                recipeMaterial.setOrderIndex(position+1);
                ItemLayoutRecipeMaterialBinding materialBinding = DataBindingUtil.bind(holder.itemView);
                materialBinding.setMaterial(recipeMaterial);
                materialBinding.setViewModel(mViewModel);
                materialBinding.setLifecycleOwner(PublishRecipeActivity.this);

                materialBinding.ivRecipeMaterialDelete.setOnClickListener(v -> {
                    mViewModel.recipe.getValue().getRecipeMaterialList().remove(recipeMaterial);
                    mViewModel.recipeMaterialListData.postValue(mViewModel.recipe.getValue().getRecipeMaterialList());
                });
            }
        });
        materialItemTouchHelper = new ItemTouchHelper(new RcvDragVerticalCallback(mDataBinding.rcvPublishRecipeMaterial,mViewModel.recipe.getValue().getRecipeMaterialList()));
        mViewModel.canEditMaterial.observe(this,aBoolean -> {
            materialItemTouchHelper.attachToRecyclerView(aBoolean?mDataBinding.rcvPublishRecipeMaterial:null);
        });

        //步骤adapter 及拖动
        mDataBinding.rcvPublishRecipeStep.setAdapter(new CmnRcvAdapter<RecipeStep>(
                this, R.layout.item_layout_recipe_step,
                mViewModel.recipeStepListData
        ) {
            @Override
            public void convert(CmnViewHolder holder, RecipeStep recipeStep, int position) {
                recipeStep.setOrderIndex(position+1);
                ItemLayoutRecipeStepBinding recipeStepBinding = DataBindingUtil.bind(holder.itemView);
                recipeStepBinding.setStep(recipeStep);
                recipeStepBinding.setViewModel(mViewModel);
                recipeStepBinding.setLifecycleOwner(PublishRecipeActivity.this);
                recipeStepBinding.ivRecipeStepDelete.setOnClickListener(v -> {
                    mViewModel.recipe.getValue().getRecipeStepList().remove(recipeStep);
                    mViewModel.recipeStepListData.postValue(mViewModel.recipe.getValue().getRecipeStepList());
                });

                recipeStepBinding.flRecipeStepAddImg.setOnClickListener(v ->
                        UpLoadUtils.upLoadSingleImage(
                                PublishRecipeActivity.this,
                                QiNiuUtil.Prefix.IMAGE_RECIPE_STEP,
                                path -> recipeStep.setStepImg(path.get(0))
                        )
                );
            }
        });

        stepItemTouchHelper = new ItemTouchHelper(new RcvDragVerticalCallback(mDataBinding.rcvPublishRecipeStep,mViewModel.recipe.getValue().getRecipeStepList()));
        mViewModel.canEditStep.observe(this,aBoolean -> {
            stepItemTouchHelper.attachToRecyclerView(aBoolean?mDataBinding.rcvPublishRecipeStep:null);
        });

        mDataBinding.tvPublishRecipeSortName.setOnClickListener(v -> {
            Intent intent = new Intent(this,RecipeSortSelectActivity.class);
            intent.putExtra("sortId",mViewModel.recipe.getValue().getSortId());
            startActivityForResult(intent,0);
        });

        mDataBinding.tvPublishRecipeBack.setOnClickListener(v -> onBackPressed());

        mViewModel.uiChange.publishSuccess.observe(this,recipeIdStr -> {
            EventBus.getDefault().post(new BusEvent.PublishRecipeSuccess());
            if (TextUtils.isEmpty(recipeIdStr)){
                ToastyUtils.show(R.string.text_update_success);
            }
            this.finish();

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == RESULT_OK && data !=null){
            mViewModel.recipe.getValue().setSortId(data.getStringExtra("sortId"));
            mDataBinding.tvPublishRecipeSortName.setText(data.getStringExtra("sortName"));
        }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.text_dialog_title)
                .setMessage(R.string.text_confirm_quit)
                .setPositiveButton(R.string.text_confirm, (dialog, which) -> {
                    super.onBackPressed();
                })
                .setNegativeButton(R.string.text_cancel, null)
                .setCancelable(false)// 设置builder不可被取消
                .show();
    }



}