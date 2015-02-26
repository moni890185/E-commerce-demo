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
    "id",
    "sizes"
})
public class Image {

  @JsonProperty("id")
  private String id;
  @JsonProperty("sizes")
  private Sizes sizes;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  public Image withId(String id) {
    this.id = id;
    return this;
  }

  @JsonProperty("sizes")
  public Sizes getSizes() {
    return sizes;
  }

  @JsonProperty("sizes")
  public void setSizes(Sizes sizes) {
    this.sizes = sizes;
  }

  public Image withSizes(Sizes sizes) {
    this.sizes = sizes;
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