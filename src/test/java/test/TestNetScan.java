package test;

import pers.badger.net.NetScan;

/**
 * Created by badger on 16/6/16.
 */

public class TestNetScan {

    public static void main(String[] args) {
        new TestNetScan().processStart();
    }

    public void processStart() {

        String[] ipList = new String[]{"www.shengshuge.com"};
        int maxPort = 500;
        int split = 50;
        for (String ipStr : ipList) {
            for (int i = 0; i < maxPort; i += split) {
                new NetScan(ipStr, i, i + split).start();
            }
        }
    }
}
