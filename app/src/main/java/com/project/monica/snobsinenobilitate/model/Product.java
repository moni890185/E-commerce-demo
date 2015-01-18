package com.project.monica.snobsinenobilitate.model;

/**
 * Created by monica on 09/01/2015.
 */
public class Product {

    private Double price;
    private Integer imageDrawable;
    private String imageDescription;

    /**
     * @param price            product price
     * @param imageDrawable    id drawable
     * @param imageDescription description item
     */
    public Product(Double price, Integer imageDrawable, String imageDescription) {
        this.price = price;
        this.imageDrawable = imageDrawable;
        this.imageDescription = imageDescription;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
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
