package com.project.monica.openreplyproject.model;

/**
 * Created by monica on 09/01/2015.
 */
public class Product {

    private Integer price;
    private Integer imageDrawable;

    /**
     * @param price         product price
     * @param imageDrawable id drawable
     */
    public Product(Integer price, Integer imageDrawable) {
        this.price = price;
        this.imageDrawable = imageDrawable;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Integer imageDrawable) {
        this.imageDrawable = imageDrawable;
    }


}
