
package com.example.vincent.eip.Network;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hours",
        "idServices",
        "nameService",
        "price",
        "sector"
})
public class Serv {

    @JsonProperty("hours")
    private String hours;
    @JsonProperty("idServices")
    private Integer idServices;
    @JsonProperty("nameService")
    private String nameService;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("sector")
    private String sector;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hours")
    public String getHours() {
        return hours;
    }

    @JsonProperty("hours")
    public void setHours(String hours) {
        this.hours = hours;
    }

    @JsonProperty("idServices")
    public Integer getIdServices() {
        return idServices;
    }

    @JsonProperty("idServices")
    public void setIdServices(Integer idServices) {
        this.idServices = idServices;
    }

    @JsonProperty("nameService")
    public String getNameService() {
        return nameService;
    }

    @JsonProperty("nameService")
    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("sector")
    public String getSector() {
        return sector;
    }

    @JsonProperty("sector")
    public void setSector(String sector) {
        this.sector = sector;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}