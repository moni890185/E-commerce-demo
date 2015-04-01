package com.project.monica.snobsinenobilitate.models.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sizeName",
    "width",
    "height",
    "url"
})
public class Medium {

  @JsonProperty("sizeName")
  private String sizeName;
  @JsonProperty("width")
  private Integer width;
  @JsonProperty("height")
  private Integer height;
  @JsonProperty("url")
  private String url;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("sizeName")
  public String getSizeName() {
    return sizeName;
  }

  @JsonProperty("sizeName")
  public void setSizeName(String sizeName) {
    this.sizeName = sizeName;
  }

  public Medium withSizeName(String sizeName) {
    this.sizeName = sizeName;
    return this;
  }

  @JsonProperty("width")
  public Integer getWidth() {
    return width;
  }

  @JsonProperty("width")
  public void setWidth(Integer width) {
    this.width = width;
  }

  public Medium withWidth(Integer width) {
    this.width = width;
    return this;
  }

  @JsonProperty("height")
  public Integer getHeight() {
    return height;
  }

  @JsonProperty("height")
  public void setHeight(Integer height) {
    this.height = height;
  }

  public Medium withHeight(Integer height) {
    this.height = height;
    return this;
  }

  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(String url) {
    this.url = url;
  }

  public Medium withUrl(String url) {
    this.url = url;
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