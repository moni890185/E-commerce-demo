package com.project.monica.snobsinenobilitate.models.pojo.collection;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monica on 09/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

  @JsonProperty("id")
  private String id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("type")
  private String type;
  @JsonProperty("currency")
  private String currency;
  @JsonProperty("price")
  private Integer price;
  @JsonProperty("priceLabel")
  private String priceLabel;
  @JsonProperty("inStock")
  private Boolean inStock;
  @JsonProperty("locale")
  private String locale;
  @JsonProperty("description")
  private String description;
  @JsonProperty("clickUrl")
  private String clickUrl;
  @JsonProperty("image")
  private Image image;
  @JsonProperty("colors")
  private List<Color> colors = new ArrayList<Color>();
  @JsonProperty("sizes")
  private List<Size> sizes = new ArrayList<Size>();
  @JsonProperty("categories")
  private List<Category> categories = new ArrayList<Category>();
  @JsonProperty("seeMoreLabel")
  private String seeMoreLabel;
  @JsonProperty("seeMoreUrl")
  private String seeMoreUrl;
  @JsonProperty("extractDate")
  private String extractDate;
  @JsonProperty("favoriteCount")
  private Integer favoriteCount;
  @JsonProperty("salePrice")
  private Double salePrice;
  @JsonProperty("salePriceLabel")
  private String salePriceLabel;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public Product()
  {

  }

  public Product(String code, String name, String type, String currency, Integer price,
      String priceLabel, Boolean inStock, String locale, String description,
      String clickUrl, Image image,
      List<Color> colors,
      List<Size> sizes,
      List<Category> categories, String seeMoreLabel, String seeMoreUrl,
      String extractDate, Integer favoriteCount, Double salePrice, String salePriceLabel) {
    this.id = code;
    this.name = name;
    this.type = type;
    this.currency = currency;
    this.price = price;
    this.priceLabel = priceLabel;
    this.inStock = inStock;
    this.locale = locale;
    this.description = description;
    this.clickUrl = clickUrl;
    this.image = image;
    this.colors = colors;
    this.sizes = sizes;
    this.categories = categories;
    this.seeMoreLabel = seeMoreLabel;
    this.seeMoreUrl = seeMoreUrl;
    this.extractDate = extractDate;
    this.favoriteCount = favoriteCount;
    this.salePrice = salePrice;
    this.salePriceLabel = salePriceLabel;
  }

  @JsonProperty("id")
  public String getCode() {
    return id;
  }

  @JsonProperty("id")
  public void setCode(String id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  @JsonProperty("currency")
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @JsonProperty("price")
  public Integer getPrice() {
    return price;
  }

  @JsonProperty("price")
  public void setPrice(Integer price) {
    this.price = price;
  }

  @JsonProperty("priceLabel")
  public String getPriceLabel() {
    return priceLabel;
  }

  @JsonProperty("priceLabel")
  public void setPriceLabel(String priceLabel) {
    this.priceLabel = priceLabel;
  }

  @JsonProperty("inStock")
  public Boolean getInStock() {
    return inStock;
  }

  @JsonProperty("inStock")
  public void setInStock(Boolean inStock) {
    this.inStock = inStock;
  }

  @JsonProperty("locale")
  public String getLocale() {
    return locale;
  }

  @JsonProperty("locale")
  public void setLocale(String locale) {
    this.locale = locale;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("clickUrl")
  public String getClickUrl() {
    return clickUrl;
  }

  @JsonProperty("clickUrl")
  public void setClickUrl(String clickUrl) {
    this.clickUrl = clickUrl;
  }

  @JsonProperty("image")
  public Image getImage() {
    return image;
  }

  @JsonProperty("image")
  public void setImage(Image image) {
    this.image = image;
  }

  @JsonProperty("colors")
  public List<Color> getColors() {
    return colors;
  }

  @JsonProperty("colors")
  public void setColors(List<Color> colors) {
    this.colors = colors;
  }

  @JsonProperty("sizes")
  public List<Size> getSizes() {
    return sizes;
  }

  @JsonProperty("sizes")
  public void setSizes(List<Size> sizes) {
    this.sizes = sizes;
  }

  @JsonProperty("categories")
  public List<Category> getCategories() {
    return categories;
  }

  @JsonProperty("categories")
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @JsonProperty("seeMoreLabel")
  public String getSeeMoreLabel() {
    return seeMoreLabel;
  }

  @JsonProperty("seeMoreLabel")
  public void setSeeMoreLabel(String seeMoreLabel) {
    this.seeMoreLabel = seeMoreLabel;
  }

  @JsonProperty("seeMoreUrl")
  public String getSeeMoreUrl() {
    return seeMoreUrl;
  }

  @JsonProperty("seeMoreUrl")
  public void setSeeMoreUrl(String seeMoreUrl) {
    this.seeMoreUrl = seeMoreUrl;
  }

  @JsonProperty("extractDate")
  public String getExtractDate() {
    return extractDate;
  }

  @JsonProperty("extractDate")
  public void setExtractDate(String extractDate) {
    this.extractDate = extractDate;
  }

  @JsonProperty("favoriteCount")
  public Integer getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favoriteCount")
  public void setFavoriteCount(Integer favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonProperty("salePrice")
  public Double getSalePrice() {
    return salePrice;
  }

  @JsonProperty("salePrice")
  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  @JsonProperty("salePriceLabel")
  public String getSalePriceLabel() {
    return salePriceLabel;
  }

  @JsonProperty("salePriceLabel")
  public void setSalePriceLabel(String salePriceLabel) {
    this.salePriceLabel = salePriceLabel;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperties(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override public String toString() {
    return "Product{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", currency='" + currency + '\'' +
        ", price=" + price +
        ", priceLabel='" + priceLabel + '\'' +
        ", inStock=" + inStock +
        ", locale='" + locale + '\'' +
        ", description='" + description + '\'' +
        ", clickUrl='" + clickUrl + '\'' +
        ", image=" + image +
        ", colors=" + colors +
        ", sizes=" + sizes +
        ", categories=" + categories +
        ", seeMoreLabel='" + seeMoreLabel + '\'' +
        ", seeMoreUrl='" + seeMoreUrl + '\'' +
        ", extractDate='" + extractDate + '\'' +
        ", favoriteCount=" + favoriteCount +
        ", salePrice=" + salePrice +
        ", salePriceLabel='" + salePriceLabel + '\'' +
        ", additionalProperties=" + additionalProperties +
        '}';
  }
}