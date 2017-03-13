package test;

import pers.badger.FileIO.FileRecord;
import pers.badger.FileIO.LoadDicFile;
import pers.badger.server.SshTry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by badger on 2017/3/11.
 */
public class TestSshScan {
    Map<String, ArrayList<String>> dicMap;

    public static void main(String[] args) {
        new TestSshScan().process();
    }

    public void process() {
        FileRecord.fileRecord.record("load dics...");

        //加载主机字典
        ArrayList<String> hostDic = new LoadDicFile("dic/ssh/host").getFileLines();

        //加载用户名字典
        ArrayList<String> userDic = new LoadDicFile("dic/ssh/user").getFileLines();

        //加载密码字典
//        ArrayList<String> passDic = new LoadDicFile("dic/ssh/1pass00.txt").getFileLines();
//        String[] dics = {"ssh/1pass00.txt", "ssh/1pass01.txt", "ssh/1pass02.txt", "ssh/1pass03.txt"};
        String[] dics = {"PasswordDic_server"};
        dicMap = new HashMap<String, ArrayList<String>>();
        for (String dic : dics) {
            dicMap.put(dic, new LoadDicFile("dic/" + dic).getFileLines());
        }


        int times = 0;
        int threadCount = 0;

        FileRecord.fileRecord.record("processing...");
        fullTry:
        for (String host : hostDic) {
            for (String userName : userDic) {
                for (Map.Entry<String, ArrayList<String>> item : dicMap.entrySet()) {
                    threadCount++;
                    System.out.println("thread " + threadCount + "\t\t user:" + userName + "\t\t usingDic" + item.getKey());
                    SshTry sshTry = new SshTry(host, userName, item.getValue());
                    sshTry.setThreadIndex(threadCount);
                    sshTry.start();
                }
            }
        }

        FileRecord.fileRecord.record("processing with " + threadCount + " thread");
//        System.out.println("用户字典：" + userDic.size() + "个用户");
//        System.out.println("密码字典：" + passDic.size() + "个密码");
//        System.out.println("尝试" + times + "次");
    }

}