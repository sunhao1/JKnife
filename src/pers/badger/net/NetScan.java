package pers.badger.net;

/**
 * Created by badger on 16/6/16.
 */

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

public class NetScan extends Thread {

    public String ipStr;
    public int maxPortThread;
    public int portStart;
    public int portEnd;

    Socket ss = null;

    public NetScan(String ip, int startPort, int endPort) {
        this.ipStr = getIP(ip);
        this.maxPortThread = 100;
        this.portStart = startPort;
        this.portEnd = endPort;
    }

    @Override
    public void run() {
        for (int i = portStart; i < portEnd; i++) {
            try {
                ss = new Socket(ipStr, i);
                System.err.print("\nIP:" + ipStr + ",开放端口:" + i + "\n");
            } catch (IOException e) {
            }
        }
    }

    private String getIP(String hostName) {
        String ipAddress = "";
        try {
            ipAddress = InetAddress.getByName(hostName).getHostAddress();
        } catch (Exception e) {

        }
        return ipAddress;
    }
}
