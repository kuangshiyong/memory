package com.memory.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

// SV-2101 音频卡 数据结构所用

/**
 * TCP/IP Address Utility Class:
 *
 * 模拟C#：
 *
 * IPAddress.TryParse(IpStr, out ipaddress)
 * BitConverter.ToUInt32(ipaddress.GetAddressBytes(), 0)
 *
 */
public class IPAddress {
    private static final Logger logger = LoggerFactory.getLogger(IPAddress.class);

    public final static long parseAddress(String ipaddr) {
        //  Check if the string is valid
        if ( ipaddr == null || ipaddr.length() < 7 || ipaddr.length() > 15)
            return 0;

        //  Check the address string, should be n.n.n.n format
        StringTokenizer token = new StringTokenizer(ipaddr,".");
        if ( token.countTokens() != 4)
            return 0;

        long ipInt = 0;
        int count = 0;
        while ( token.hasMoreTokens()) {
            //  Get the current token and convert to an integer value
            String ipNum = token.nextToken();
            try {
                Long ipVal = Long.valueOf(ipNum);
                if ( ipVal < 0 || ipVal > 255)
                    return 0;

                ipInt |= ipVal << ( count * 8);
            }
            catch (NumberFormatException e) {
                logger.error("parse ip for playback");
                e.printStackTrace();
                return 0;
            }
            count ++;
        }

        //  Return the integer address
        return ipInt;
    }

    public static String getLocalIp() {
        String ip = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("get local ip for playback");
            e.printStackTrace();
        }
        return ip;
    }
}
