package pers.badger.util;

import java.net.InetAddress;

/**
 * Created by badger on 2017/3/29.
 */
public class HostConvert {

    public String getIP(String hostName) {
        String ipAddress = "";
        try {
            ipAddress = InetAddress.getByName(hostName).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress;
    }
}
