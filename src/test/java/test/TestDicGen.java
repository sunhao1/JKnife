package test;

import pers.badger.dic.HostDicGen;

/**
 * Created by badger on 2017/3/12.
 */
public class TestDicGen {
    public static void main(String[] args) {
        HostDicGen hostDicGen = new HostDicGen("223.131.0.0-223.131.60.250");
        hostDicGen.process();
    }
}