package pt.andronikus.client.dto;

import org.junit.jupiter.api.Test;
import pt.andronikus.client.constantes.FulfillmentParamsAtt;
import pt.andronikus.client.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FulfillmentParamsTest {
    @Test
    public void shouldPrintValidJsonWithArrayParams(){
        FulfillmentParams fulfillmentParams = new FulfillmentParams();

        final String goldValue = "{\"fulfillment_parameters\":{\"apnList\":[\"apn1\",\"apn2\"],\"inOperatorId\":\"0\",\"migFlag\":\"-1\"}}";

        List<String> apns = new ArrayList<>();
        apns.add("apn1");
        apns.add("apn2");

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.OPERATOR_ID, "0");
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, "-1");
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APN_LIST, apns);

        assertEquals(goldValue,JSONUtils.toJSON(fulfillmentParams));
    }
}