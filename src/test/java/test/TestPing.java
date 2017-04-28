package test;

import pers.badger.FileIO.LoadDicFile;
import pers.badger.net.NetPing;

import java.util.ArrayList;

/**
 * Created by badger on 2017/3/29.
 */
public class TestPing {
    private ArrayList<String> hostDic;
    ArrayList<ArrayList<String>> hostDicSplit;

    public static void main(String[] args) {
        new TestPing().process();

    }

    private void process() {

        //加载主机字典
        hostDic = new LoadDicFile("dic/ResultOut").getFileLines();
        hostDicSplit = new ArrayList<ArrayList<String>>();

        int hostCount = hostDic.size();
        int maxThread = 500;
        int split = hostCount / maxThread + 1;

        ArrayList<String> dicSplit;
        for (int i = 0; i < hostCount; i += split) {
            dicSplit = new ArrayList<String>();
            for (int index = i; index < i + split && index < hostCount; index++) {
                dicSplit.add(hostDic.get(index));
            }
            hostDicSplit.add(dicSplit);
        }

        try {

            for (ArrayList<String> hosts : hostDicSplit) {
                new NetPing(hosts).start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
