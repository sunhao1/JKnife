package pers.badger.net;

import pers.badger.FileIO.FileRecord;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by badger on 2017/3/12.
 */
public class HostPortCheck extends Thread {
    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    private int threadCount = 0;

    private int portNo;
    private ArrayList<String> hostList;

    private Socket ss = null;
    private String msg;

    public HostPortCheck(ArrayList<String> hostList, int portNo) {
        this.hostList = hostList;
        this.portNo = portNo;
    }

    @Override
    public void run() {
        System.out.println("thread " + threadCount + " start!");
        for (String host : hostList) {
            try {
                ss = new Socket(host, portNo);
                msg = "Host:\t" + host + "\t开放端口:" + portNo + "";
                System.out.println("Host:" + host + "\t开放端口:" + portNo + "");
                FileRecord.fileRecord.record(portNo + "\t" + host);
            } catch (Exception e) {
                msg = "Host:\t" + host + "\tpass";
                System.out.println(msg);
            }
        }

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
