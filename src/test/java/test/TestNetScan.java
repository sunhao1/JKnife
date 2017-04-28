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

        String[] ipList = new String[]{"120.195.40.207"};
        int maxPort = 11000;
        int split = 30;
        for (String ipStr : ipList) {
            for (int i = 0; i < maxPort; i += split) {
                new NetScan(ipStr, i, i + split).start();
            }
        }
    }
}
