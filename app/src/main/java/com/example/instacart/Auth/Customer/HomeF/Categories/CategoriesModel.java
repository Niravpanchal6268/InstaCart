package com.example.instacart.Auth.Customer.HomeF.Categories;

public class CategoriesModel {
    private  String CategoriesImage,CategoriesName;

    public CategoriesModel(String categoriesImage, String categoriesName) {
        CategoriesImage = categoriesImage;
        CategoriesName = categoriesName;
    }

    public CategoriesModel() {
    }

    public String getCategoriesImage() {
        return CategoriesImage;
    }

    public void setCategoriesImage(String categoriesImage) {
        CategoriesImage = categoriesImage;
    }

    public String getCategoriesName() {
        return CategoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        CategoriesName = categoriesName;
    }

    @Override
    public String toString() {
        return "CategoriesModel{" +
                "CategoriesImage='" + CategoriesImage + '\'' +
                ", CategoriesName='" + CategoriesName + '\'' +
                '}';
    }
}
