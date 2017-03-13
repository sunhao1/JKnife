package pers.badger.dic;

import pers.badger.FileIO.FileRecord;
import pers.badger.util.Host;
import pers.badger.util.Lookup;

/**
 * Created by badger on 2017/3/12.
 */
public class HostDicGen {
    private String hostStart;
    private String hostEnd;

    public HostDicGen(String hostStart, String hostEnd) {
        this.hostStart = hostStart;
        this.hostEnd = hostEnd;
    }

    public HostDicGen(String hostArea) {
        this.hostStart = hostArea.split("-")[0];
        this.hostEnd = hostArea.split("-")[1];
    }

    public HostDicGen() {

    }

    public void process() {
        Host startHost = new Host(hostStart);
        Host endHost = new Host(hostEnd);
        String ipStr;

        //只解析16位以上掩码IP段，否则数据量太大，难以处理
        if (startHost.getA() == endHost.getA() && startHost.getB() == endHost.getB() && startHost.getC() <= endHost.getC() && startHost.getD() <= endHost.getD()) {
            for (int typeC = startHost.getC(); typeC <= endHost.getC(); typeC++) {
                for (int typeD = startHost.getD(); typeD <= endHost.getD(); typeD++) {
                    ipStr = startHost.getA() + "." + startHost.getB() + "." + typeC + "." + typeD;
                    FileRecord.fileRecord.record(ipStr);
                    System.out.println(ipStr);
                }
            }
        }
    }
}
