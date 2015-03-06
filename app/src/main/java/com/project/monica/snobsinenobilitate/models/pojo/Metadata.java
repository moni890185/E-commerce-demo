
package com.project.monica.snobsinenobilitate.models.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "category",
    "showSizeFilter",
    "showColorFilter",
    "freeTextSearches",
    "offset",
    "limit",
    "total"
})
public class Metadata {

    @JsonProperty("category")
    private Category category;
    @JsonProperty("showSizeFilter")
    private Boolean showSizeFilter;
    @JsonProperty("showColorFilter")
    private Boolean showColorFilter;
    @JsonProperty("freeTextSearches")
    private List<String> freeTextSearches = new ArrayList<String>();
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("total")
    private Integer total;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    public Metadata withCategory(Category category) {
        this.category = category;
        return this;
    }

    @JsonProperty("showSizeFilter")
    public Boolean getShowSizeFilter() {
        return showSizeFilter;
    }

    @JsonProperty("showSizeFilter")
    public void setShowSizeFilter(Boolean showSizeFilter) {
        this.showSizeFilter = showSizeFilter;
    }

    public Metadata withShowSizeFilter(Boolean showSizeFilter) {
        this.showSizeFilter = showSizeFilter;
        return this;
    }

    @JsonProperty("showColorFilter")
    public Boolean getShowColorFilter() {
        return showColorFilter;
    }

    @JsonProperty("showColorFilter")
    public void setShowColorFilter(Boolean showColorFilter) {
        this.showColorFilter = showColorFilter;
    }

    public Metadata withShowColorFilter(Boolean showColorFilter) {
        this.showColorFilter = showColorFilter;
        return this;
    }

    @JsonProperty("freeTextSearches")
    public List<String> getFreeTextSearches() {
        return freeTextSearches;
    }

    @JsonProperty("freeTextSearches")
    public void setFreeTextSearches(List<String> freeTextSearches) {
        this.freeTextSearches = freeTextSearches;
    }

    public Metadata withFreeTextSearches(List<String> freeTextSearches) {
        this.freeTextSearches = freeTextSearches;
        return this;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Metadata withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Metadata withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Metadata withTotal(Integer total) {
        this.total = total;
        return this;
    }

  public Metadata()
  {}

  public Metadata(String categoryCode, String categoryName)
  {
    category = new Category(categoryCode, categoryName);
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
    return "Metadata{" +
        "category=" + category +
        ", showSizeFilter=" + showSizeFilter +
        ", showColorFilter=" + showColorFilter +
        ", freeTextSearches=" + freeTextSearches +
        ", offset=" + offset +
        ", limit=" + limit +
        ", total=" + total +
        ", additionalProperties=" + additionalProperties +
        '}';
  }
}
