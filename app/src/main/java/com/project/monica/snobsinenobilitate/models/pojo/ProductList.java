package com.project.monica.snobsinenobilitate.models.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "products"
})


public class ProductList {
  @JsonProperty("products")
  private List<Product> products;

  @JsonProperty("metadata")
  private Metadata metadata;

  @JsonProperty("metadata")
  public Metadata getMetadata() {
    return metadata;
  }

  @JsonProperty("metadata")
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }


  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  private Map<String, Object> additionalProperties = new HashMap<String, Object>();


  public ProductList() {
  }

  public ProductList(String categoryCode, String categoryName, List<Product> products)
  {
    metadata = new Metadata(categoryCode, categoryName);
    this.products = products;
  }

  public ProductList(Metadata metadata, List<Product> products)
  {
    this.metadata = metadata;
    this.products = products;
  }


  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperties(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public String productArrayToCommaList() {
    if (products != null) {
      StringBuilder sb = new StringBuilder();

      for (Product product : products) {
        sb.append(product.getCode());
        sb.append(',');
      }

      int lastCommaIndex = sb.lastIndexOf(",");
      if (lastCommaIndex > -1) {
        sb.deleteCharAt(lastCommaIndex);
      }

      return sb.toString();
    }

    return null;
  }

  @Override public String toString() {
    return "ProductList{" +
        "products=" + products +
        ", metadata=" + metadata +
        ", additionalProperties=" + additionalProperties +
        '}';
  }
}