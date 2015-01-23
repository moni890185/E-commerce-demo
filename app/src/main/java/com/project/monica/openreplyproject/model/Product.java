package com.project.monica.openreplyproject.model;

import com.project.monica.openreplyproject.R;

/**
 * Created by monica on 09/01/2015.
 */
public class Product {
    private String mPrice;
    private Integer imageDrawable;
    private String mTitle;

    /**
     * @param price        product mPrice
     * @param imageDrawable id drawable
     * @param title         product name
     */

    public Product(String price, Integer imageDrawable, String title) {
        this.mPrice = price;
        this.imageDrawable = imageDrawable;
        this.mTitle = title;
    }

    public static Product[] PRODUCTS = new Product[]{
            new Product("130.00", R.drawable.img_card_view, "Textured chinos"),
            new Product("120.00", R.drawable.img_card_view_jacket, "Nappy jacket"),
            new Product("141.00", R.drawable.img_card_view_man, "Dark sweater"),
            new Product("115.00", R.drawable.img_card_view_man1, "Slim-fit shirt"),
            new Product("135.00", R.drawable.img_card_view_man2, "Silk shirt"),
            new Product("110.00", R.drawable.img_card_view_woman1, "Long suede cape"),
            new Product("170.00", R.drawable.img_card_view_women, "Cape coat"),
            new Product("165.00", R.drawable.img_card_view_shoes, "Taupe mocassin")
    };

    public static Product getProduct(int id) {
        for (Product p : PRODUCTS) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Integer getId() {
        return mTitle.hashCode() + imageDrawable.hashCode();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Integer imageDrawable) {
        this.imageDrawable = imageDrawable;
    }


}
