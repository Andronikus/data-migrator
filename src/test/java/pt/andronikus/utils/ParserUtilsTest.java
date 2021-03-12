package pt.andronikus.utils;

import org.junit.jupiter.api.Test;
import pt.andronikus.entities.base.ApnInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserUtilsTest {

    @Test
    public void shouldParseOneApnFromListWithIpAddress1AndIpAddress2(){
        String apnList = "m2minternet,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,IPV4:false:|";

        List<ApnInfo> apnInfoList = ParserUtils.parseApnList(apnList);

        assertEquals(apnInfoList.size(), 1);
        assertEquals(apnInfoList.get(0).getId(), "m2minternet", "apnId should be m2minternet");
        assertEquals(apnInfoList.get(0).getType(), "PUBLIC", "apn type should be PUBLIC");
        assertEquals(apnInfoList.get(0).getIpType(), "IPV4", "apn IP type should be IPV4");

        assertNotNull(apnInfoList.get(0).getIpAddressList1(), "apn address list 1 cannot be null");
        assertEquals(apnInfoList.get(0).getIpAddressList1().getType(), "IPV4");
        assertTrue(apnInfoList.get(0).getIpAddressList1().isFixed());
        assertEquals(apnInfoList.get(0).getIpAddressList1().getFixedIpRanges(), "10.10.1.1/16;192.168.1.1/32");

        assertNotNull(apnInfoList.get(0).getIpAddressList2(), "apn address list 2 cannot be null");
        assertEquals(apnInfoList.get(0).getIpAddressList2().getType(), "IPV4");
        assertFalse(apnInfoList.get(0).getIpAddressList2().isFixed());
        assertNull(apnInfoList.get(0).getIpAddressList2().getFixedIpRanges());
    }

    @Test
    public void shouldParseOneApnFromListWithIpAddress1Only(){
        String apnList = "m2minternet,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,|";

        List<ApnInfo> apnInfoList = ParserUtils.parseApnList(apnList);

        assertEquals(apnInfoList.size(), 1);
        assertEquals(apnInfoList.get(0).getId(), "m2minternet", "apnId should be m2minternet");
        assertEquals(apnInfoList.get(0).getType(), "PUBLIC", "apn type should be PUBLIC");
        assertEquals(apnInfoList.get(0).getIpType(), "IPV4", "apn IP type should be IPV4");

        assertNotNull(apnInfoList.get(0).getIpAddressList1(), "apn address list 1 cannot be null");
        assertEquals(apnInfoList.get(0).getIpAddressList1().getType(), "IPV4");
        assertTrue(apnInfoList.get(0).getIpAddressList1().isFixed());
        assertEquals(apnInfoList.get(0).getIpAddressList1().getFixedIpRanges(), "10.10.1.1/16;192.168.1.1/32");

        assertNull(apnInfoList.get(0).getIpAddressList2());
    }

    @Test
    public void shouldParseOneApnFromListWithIpAddress2Only(){
        String apnList = "m2minternet,PUBLIC,IPV4,,IPV4:false:|";

        List<ApnInfo> apnInfoList = ParserUtils.parseApnList(apnList);

        assertEquals(apnInfoList.size(), 1);
        assertEquals(apnInfoList.get(0).getId(), "m2minternet", "apnId should be m2minternet");
        assertEquals(apnInfoList.get(0).getType(), "PUBLIC", "apn type should be PUBLIC");
        assertEquals(apnInfoList.get(0).getIpType(), "IPV4", "apn IP type should be IPV4");

        assertNull(apnInfoList.get(0).getIpAddressList1());

        assertNotNull(apnInfoList.get(0).getIpAddressList2(), "apn address list 2 cannot be null");
        assertEquals(apnInfoList.get(0).getIpAddressList2().getType(), "IPV4");
        assertFalse(apnInfoList.get(0).getIpAddressList2().isFixed());
        assertNull(apnInfoList.get(0).getIpAddressList2().getFixedIpRanges());
    }
}