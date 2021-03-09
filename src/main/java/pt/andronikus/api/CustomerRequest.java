package pt.andronikus.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CustomerRequest {
    public static final String CUSTOMER_PATH = "customer";

    @JsonProperty
    @NotEmpty(message = "Customer name should not be null!")
    private String name;

    @JsonProperty
    private OfferParameters offerParams = new OfferParameters();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfferParameters getOfferParams() {
        return offerParams;
    }

    public void setOfferParams(OfferParameters offerParams) {
        this.offerParams = offerParams;
    }
}

class OfferParameters {
    @JsonProperty
    @NotEmpty(message = "offer name should not be null")
    private String name;
    @JsonProperty
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
