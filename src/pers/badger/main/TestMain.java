package pers.badger.main;

import pers.badger.net.NetScan;

/**
 * Created by badger on 16/6/16.
 */

public class TestMain {

    public static void main(String[] args) {
        new TestMain().processStart();
    }

    public void processStart() {

        String[] ipList = new String[]{"192.168.100.253"};
        for (String ipStr : ipList) {
            for (int i = 0; i < 5000; i += 100) {
                new NetScan(ipStr, i, i + 100).start();
            }
        }
    }
}
