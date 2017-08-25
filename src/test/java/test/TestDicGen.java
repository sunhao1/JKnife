package test;

import pers.badger.dic.HostDicGen;

/**
 * Created by badger on 2017/3/12.
 */
public class TestDicGen {
    public static void main(String[] args) {
        HostDicGen hostDicGen = new HostDicGen("192.168.0.1-192.168.0.254");
        System.out.println();
        hostDicGen.process();
    }
}