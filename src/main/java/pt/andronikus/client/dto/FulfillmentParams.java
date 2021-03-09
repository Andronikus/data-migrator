package pt.andronikus.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.andronikus.client.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

public class FulfillmentParams extends BaseDto{
    private final Map<String, Object> fulfilmentParams;

    public FulfillmentParams() {
        super();
        fulfilmentParams = new HashMap<>();
    }

    @JsonProperty("fulfillment_parameters")
    public Map<String, Object> getFulfilmentParams() {
        return fulfilmentParams;
    }

    public void addFulfillmentParam(String paramName, Object paramValue){
        this.fulfilmentParams.putIfAbsent(paramName,paramValue);
    }

    @Override
    public String toString() {
        return "FulfillmentParams{" +
                "fulfilmentParams=" + fulfilmentParams +
                '}';
    }
}
