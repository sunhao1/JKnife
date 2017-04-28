package test;

import pers.badger.dic.HostDicGen;

/**
 * Created by badger on 2017/3/12.
 */
public class TestDicGen {
    public static void main(String[] args) {
        HostDicGen hostDicGen = new HostDicGen("172.16.1.0-172.16.250.250");
        System.out.println();
        hostDicGen.process();
    }
}