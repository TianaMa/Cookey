package com.demo.cook.modules.recipe.model;

public class RecipeMaterial {

    private String materialName;

    private String dosage;

    private int orderIndex;

    public RecipeMaterial(String recipeId, String materialName, String dosage) {
        this.materialName = materialName;
        this.dosage = dosage;
    }

    public RecipeMaterial() {
        super();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}