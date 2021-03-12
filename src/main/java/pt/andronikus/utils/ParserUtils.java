package pt.andronikus.utils;

import pt.andronikus.constants.Global;
import pt.andronikus.entities.base.ApnInfo;
import pt.andronikus.entities.base.TariffCostPerState;

import java.util.*;

public class ParserUtils {
    public static List<String> parseTariffs(String tariff){
        return parseToList(tariff, Global.HASH_SYMBOL);
    }

    public static List<String> parseApns(String apns){
        return parseToList(apns, Global.HASH_SYMBOL);
    }

    public static List<String> parseQosLevels(String qosLevels){
        return parseToList(qosLevels, Global.HASH_SYMBOL);
    }

    public static List<TariffCostPerState> parseTariffCostsPerState(String tariff){

        if(tariff == null || tariff.length() == 0){
            return new ArrayList<>();
        }

        Map<String, TariffCostPerState> mapTariffsCost = new HashMap<>();

        String[] tariffStateCostList = tariff.split(Global.HASH_SYMBOL);

        for(String tariffStateCost: tariffStateCostList){
            String[] costPerStateOfTariffArr = tariffStateCost.split(Global.COLON_SYMBOL);

            if(costPerStateOfTariffArr.length != 3){
                throw new IllegalArgumentException("<tariffId>:<state>:<cost> not found!");
            }

            String tariffId = costPerStateOfTariffArr[0];
            String state = costPerStateOfTariffArr[1];
            String stateCost = costPerStateOfTariffArr[2];

            TariffCostPerState tariffCostPerState = mapTariffsCost.get(tariffId);

            if(tariffCostPerState == null){
                // new tariff
                TariffCostPerState newTariffCostPerState = new TariffCostPerState();
                newTariffCostPerState.setTariffId(tariffId);
                newTariffCostPerState.setStateCost(state,stateCost);
                mapTariffsCost.put(tariffId,newTariffCostPerState);
            }else {
                tariffCostPerState.setStateCost(state,stateCost);
            }
        }

        return new ArrayList<>(mapTariffsCost.values());
    }

    public static List<ApnInfo> parseApnList(String apnsToParse){
        if(apnsToParse == null || apnsToParse.length() == 0){
            return new ArrayList<>();
        }

        List<ApnInfo> apnList = new ArrayList<>();

        String[] apns = apnsToParse.split(Global.PIPE_SYMBOL);

        for(String apn: apns){

            if (apn != null){
                String[] apnParsed = apn.split(Global.COMMA_SYMBOL);

                if (apnParsed.length < 3){
                    return new ArrayList<>();
                }

                ApnInfo newApnInfo = new ApnInfo();
                newApnInfo.setId(apnParsed[0]);
                newApnInfo.setType(apnParsed[1]);
                newApnInfo.setIpType(apnParsed[2]);

                newApnInfo.setIpAddressList1(parseIpAddressInfo(apnParsed[3]));

                if(apnParsed.length == 5){
                    newApnInfo.setIpAddressList2(parseIpAddressInfo(apnParsed[4]));
                }

                apnList.add(newApnInfo);
            }
        }

        return apnList;
    }

    private static ApnInfo.IpAddressInfo parseIpAddressInfo(String ipAddressInfo){
        if(ipAddressInfo == null || ipAddressInfo.length() == 0){
            return null;
        }

        String[] parsedInfo = ipAddressInfo.split(Global.COLON_SYMBOL);

        if(parsedInfo.length < 2){
            return null;
        }

        String ipAddressType = parsedInfo[0];
        boolean fixedIp = Boolean.parseBoolean(parsedInfo[1]);
        String fixedIpRanges = null;

        if(fixedIp){
            fixedIpRanges = parsedInfo[2].replace(Global.HASH_SYMBOL,Global.SEMICOLON_SYMBOL);
        }

        return new ApnInfo.IpAddressInfo(ipAddressType,fixedIp,fixedIpRanges);
    }

    private static List<String> parseToList(String tariff, String symbol){
        if(tariff == null || tariff.length() == 0){
            return new ArrayList<>();
        }

        return Arrays.asList(tariff.split(symbol));
    }
}
