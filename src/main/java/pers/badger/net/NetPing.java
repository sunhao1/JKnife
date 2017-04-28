package pers.badger.net;

import pers.badger.util.HostConvert;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by badger on 2017/3/29.
 */
public class NetPing extends Thread {
    private String hostName;
    private ArrayList<String> hostList;
    private String hostType;


    public NetPing(String hostName) {
        this.hostName = hostName;
        this.hostType = "single";
    }

    public NetPing(ArrayList<String> hostList) {
        this.hostList = hostList;
        this.hostType = "multiply";
    }

    @Override
    public void run() {
        if (hostType == "single") {
            pingHost(this.hostName);
        } else {
            for (String host : hostList) {
                pingHost(host);
            }
        }
    }

    private void pingHost(String testHost) {

        boolean status = false;
        String ip = new HostConvert().getIP(testHost);
        if (ip != null) {
            try {
                status = InetAddress.getByName(ip).isReachable(200);
                if (status) {
                    System.out.println(ip + "\t status is " + status);
                }
            } catch (Exception e) {

            }
        }
    }
}
