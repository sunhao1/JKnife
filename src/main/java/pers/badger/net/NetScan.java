package pers.badger.net;

/**
 * Created by badger on 16/6/16.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.InetAddress;
import java.util.ArrayList;

public class NetScan extends Thread {
    static int threadCount = 0;

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
        System.out.println("Start scaning(" + ipStr + ":" + Integer.toString(this.portStart) + ")" + Integer.toString(++threadCount));
        for (int i = portStart; i < portEnd; i++) {
            try {
//                System.out.print("\b\b\b\b" + String.format("%08d", i));
                ss = new Socket(ipStr, i);
//                ss = new Socket();
//                ss.connect(new InetSocketAddress(ipStr, i), 200);
                System.err.print("\nIP:" + ipStr + ",开放端口:" + i + "\n");
            } catch (IOException e) {
            }
        }
//        System.out.println("IP scan finished!(" + Integer.toString(this.portStart) + ")" + Integer.toString(--threadCount));
    }

    private String getIP(String hostName) {
        String ipAddress = "";
        try {
            ipAddress = InetAddress.getByName(hostName).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress;
    }
}
