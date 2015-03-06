package com.project.monica.snobsinenobilitate.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Color {

  @JsonProperty("name")
  private String name;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("swatchUrl")
  private String swatchUrl;

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("swatchUrl")
  public String getSwatchUrl() {
    return swatchUrl;
  }

  @JsonProperty("swatchUrl")
  public void setSwatchUrl(String swatchUrl) {
    this.swatchUrl = swatchUrl;
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