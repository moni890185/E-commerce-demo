package com.project.monica.openreplyproject.model;

/**
 * Created by monica on 09/01/2015.
 */
public class Product {

    private Double price;
    private Integer imageDrawable;

    /**
     * @param price         product price
     * @param imageDrawable id drawable
     */
    public Product(Double price, Integer imageDrawable) {
        this.price = price;
        this.imageDrawable = imageDrawable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Integer imageDrawable) {
        this.imageDrawable = imageDrawable;
    }


}
