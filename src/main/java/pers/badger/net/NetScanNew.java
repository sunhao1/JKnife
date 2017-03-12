package pers.badger.net;

/**
 * Created by badger on 16/6/16.
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class NetScanNew extends Thread {
    static int threadCount = 0;

    public String ipStr;

    public int portStart;
    public int portEnd;

    public ArrayList<Integer> portList;

    Socket ss = null;

    public NetScanNew(String host, int startPort, int endPort) {
        this.ipStr = getIP(host);
        this.portList = new ArrayList<Integer>();
        for (int i = startPort; i < endPort; i++) {
            this.portList.add(i);
        }
    }

    public NetScanNew(String host, ArrayList<Integer> portList) {
        this.ipStr = getIP(host);
        this.portList = portList;
    }

    @Override
    public void run() {
        System.out.println("Start scaning(" + ipStr + ":" + Integer.toString(this.portStart) + ")" + Integer.toString(++threadCount));
        for (int port : portList) {
            try {
                ss = new Socket(ipStr, port);
                System.err.print("\nIP:" + ipStr + ",开放端口:" + port + "\n");
            } catch (IOException e) {
            }
        }

        System.out.println("IP scan finished!(" + Integer.toString(this.portStart) + ")" + Integer.toString(--threadCount));
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
