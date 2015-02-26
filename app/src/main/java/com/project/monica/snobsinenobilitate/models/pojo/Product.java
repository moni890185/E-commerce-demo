package com.project.monica.snobsinenobilitate.models.pojo;

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
  private Integer id;
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

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  public Product withId(Integer id) {
    this.id = id;
    return this;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  public Product withName(String name) {
    this.name = name;
    return this;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  public Product withType(String type) {
    this.type = type;
    return this;
  }

  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  @JsonProperty("currency")
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Product withCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  @JsonProperty("price")
  public Integer getPrice() {
    return price;
  }

  @JsonProperty("price")
  public void setPrice(Integer price) {
    this.price = price;
  }

  public Product withPrice(Integer price) {
    this.price = price;
    return this;
  }

  @JsonProperty("priceLabel")
  public String getPriceLabel() {
    return priceLabel;
  }

  @JsonProperty("priceLabel")
  public void setPriceLabel(String priceLabel) {
    this.priceLabel = priceLabel;
  }

  public Product withPriceLabel(String priceLabel) {
    this.priceLabel = priceLabel;
    return this;
  }

  @JsonProperty("inStock")
  public Boolean getInStock() {
    return inStock;
  }

  @JsonProperty("inStock")
  public void setInStock(Boolean inStock) {
    this.inStock = inStock;
  }

  public Product withInStock(Boolean inStock) {
    this.inStock = inStock;
    return this;
  }

  @JsonProperty("locale")
  public String getLocale() {
    return locale;
  }

  @JsonProperty("locale")
  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Product withLocale(String locale) {
    this.locale = locale;
    return this;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  public Product withDescription(String description) {
    this.description = description;
    return this;
  }

  @JsonProperty("clickUrl")
  public String getClickUrl() {
    return clickUrl;
  }

  @JsonProperty("clickUrl")
  public void setClickUrl(String clickUrl) {
    this.clickUrl = clickUrl;
  }

  public Product withClickUrl(String clickUrl) {
    this.clickUrl = clickUrl;
    return this;
  }

  @JsonProperty("image")
  public Image getImage() {
    return image;
  }

  @JsonProperty("image")
  public void setImage(Image image) {
    this.image = image;
  }

  public Product withImage(Image image) {
    this.image = image;
    return this;
  }

  @JsonProperty("colors")
  public List<Color> getColors() {
    return colors;
  }

  @JsonProperty("colors")
  public void setColors(List<Color> colors) {
    this.colors = colors;
  }

  public Product withColors(List<Color> colors) {
    this.colors = colors;
    return this;
  }

  @JsonProperty("sizes")
  public List<Size> getSizes() {
    return sizes;
  }

  @JsonProperty("sizes")
  public void setSizes(List<Size> sizes) {
    this.sizes = sizes;
  }

  public Product withSizes(List<Size> sizes) {
    this.sizes = sizes;
    return this;
  }

  @JsonProperty("categories")
  public List<Category> getCategories() {
    return categories;
  }

  @JsonProperty("categories")
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public Product withCategories(List<Category> categories) {
    this.categories = categories;
    return this;
  }

  @JsonProperty("seeMoreLabel")
  public String getSeeMoreLabel() {
    return seeMoreLabel;
  }

  @JsonProperty("seeMoreLabel")
  public void setSeeMoreLabel(String seeMoreLabel) {
    this.seeMoreLabel = seeMoreLabel;
  }

  public Product withSeeMoreLabel(String seeMoreLabel) {
    this.seeMoreLabel = seeMoreLabel;
    return this;
  }

  @JsonProperty("seeMoreUrl")
  public String getSeeMoreUrl() {
    return seeMoreUrl;
  }

  @JsonProperty("seeMoreUrl")
  public void setSeeMoreUrl(String seeMoreUrl) {
    this.seeMoreUrl = seeMoreUrl;
  }

  public Product withSeeMoreUrl(String seeMoreUrl) {
    this.seeMoreUrl = seeMoreUrl;
    return this;
  }

  @JsonProperty("extractDate")
  public String getExtractDate() {
    return extractDate;
  }

  @JsonProperty("extractDate")
  public void setExtractDate(String extractDate) {
    this.extractDate = extractDate;
  }

  public Product withExtractDate(String extractDate) {
    this.extractDate = extractDate;
    return this;
  }

  @JsonProperty("favoriteCount")
  public Integer getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favoriteCount")
  public void setFavoriteCount(Integer favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  public Product withFavoriteCount(Integer favoriteCount) {
    this.favoriteCount = favoriteCount;
    return this;
  }

  @JsonProperty("salePrice")
  public Double getSalePrice() {
    return salePrice;
  }

  @JsonProperty("salePrice")
  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  public Product withSalePrice(Double salePrice) {
    this.salePrice = salePrice;
    return this;
  }

  @JsonProperty("salePriceLabel")
  public String getSalePriceLabel() {
    return salePriceLabel;
  }

  @JsonProperty("salePriceLabel")
  public void setSalePriceLabel(String salePriceLabel) {
    this.salePriceLabel = salePriceLabel;
  }

  public Product withSalePriceLabel(String salePriceLabel) {
    this.salePriceLabel = salePriceLabel;
    return this;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperties(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}