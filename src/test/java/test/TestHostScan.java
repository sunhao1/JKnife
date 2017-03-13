package test;

import pers.badger.FileIO.LoadDicFile;
import pers.badger.net.HostPortCheck;
import pers.badger.net.NetScan;

import java.util.ArrayList;

/**
 * 根据主机字典，测试特定端口是否开放
 * Created by badger on 2017/3/12.
 */
public class TestHostScan {
    ArrayList<String> hostDic;
    ArrayList<String> portDic;
    ArrayList<ArrayList<String>> hostDicSplit;

    public static void main(String[] args) {
        new TestHostScan().processStart();
    }

    public void processStart() {
        //加载主机字典
        hostDic = new LoadDicFile("dic/host").getFileLines();
        hostDicSplit = new ArrayList<ArrayList<String>>();

        //加载端口字典
        portDic = new LoadDicFile("dic/ports").getFileLines();
        ;

        int hostCount = hostDic.size();
        int portCount = portDic.size();
        int maxThread = 500;
        int split = (hostCount * portCount) / maxThread + 1;

        ArrayList<String> dicSplit;
        for (int i = 0; i < hostCount; i += split) {
            dicSplit = new ArrayList<String>();
            for (int index = i; index < i + split && index < hostCount; index++) {
                dicSplit.add(hostDic.get(index));
            }
            hostDicSplit.add(dicSplit);
        }

        int threadCount = 0;
        for (ArrayList<String> dic : hostDicSplit) {
            for (String port : portDic) {
                int intPort = Integer.parseInt(port);
                threadCount++;
                HostPortCheck hostPortCheck = new HostPortCheck(dic, intPort);
                hostPortCheck.setThreadCount(threadCount);
                hostPortCheck.start();
            }
        }
    }
}
