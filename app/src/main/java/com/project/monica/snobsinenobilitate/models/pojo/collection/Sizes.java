
package com.project.monica.snobsinenobilitate.models.pojo.collection;

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
    "Small",
    "Medium",
    "Large",
    "Original",
    "IPhoneSmall",
    "IPhone"
})
public class Sizes {

    @JsonProperty("Small")
    Small Small;
    @JsonProperty("Medium")
    Medium Medium;
    @JsonProperty("Large")
    Large Large;
    @JsonProperty("Original")
    Original Original;
    @JsonProperty("IPhoneSmall")
    IPhoneSmall IPhoneSmall;
    @JsonProperty("IPhone")
    IPhone IPhone;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Small")
    Small getSmall() {
        return Small;
    }

    @JsonProperty("Small")
    public void setSmall(Small Small) {
        this.Small = Small;
    }

    public Sizes withSmall(Small Small) {
        this.Small = Small;
        return this;
    }

    @JsonProperty("Medium")
    public  Medium getMedium() {
        return Medium;
    }

    @JsonProperty("Medium")
    public void setMedium( Medium Medium) {
        this.Medium = Medium;
    }

    public Sizes withMedium( Medium Medium) {
        this.Medium = Medium;
        return this;
    }

    @JsonProperty("Large")
    public  Large getLarge() {
        return Large;
    }

    @JsonProperty("Large")
    public void setLarge( Large Large) {
        this.Large = Large;
    }

    public Sizes withLarge( Large Large) {
        this.Large = Large;
        return this;
    }

    @JsonProperty("Original")
    public  Original getOriginal() {
        return Original;
    }

    @JsonProperty("Original")
    public void setOriginal( Original Original) {
        this.Original = Original;
    }

    public Sizes withOriginal( Original Original) {
        this.Original = Original;
        return this;
    }

    @JsonProperty("IPhoneSmall")
    public  IPhoneSmall getIPhoneSmall() {
        return IPhoneSmall;
    }

    @JsonProperty("IPhoneSmall")
    public void setIPhoneSmall( IPhoneSmall IPhoneSmall) {
        this.IPhoneSmall = IPhoneSmall;
    }

    public Sizes withIPhoneSmall( IPhoneSmall IPhoneSmall) {
        this.IPhoneSmall = IPhoneSmall;
        return this;
    }

    @JsonProperty("IPhone")
    public  IPhone getIPhone() {
        return IPhone;
    }

    @JsonProperty("IPhone")
    public void setIPhone( IPhone IPhone) {
        this.IPhone = IPhone;
    }

    public Sizes withIPhone( IPhone IPhone) {
        this.IPhone = IPhone;
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
