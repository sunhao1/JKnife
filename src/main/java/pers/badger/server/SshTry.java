package pers.badger.server;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import pers.badger.FileIO.FileRecord;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by badger on 2017/3/11.
 */
public class SshTry extends Thread {
    private String host;
    private int port;
    private String userName;
    private ArrayList<String> passwordDic;
    private boolean isPass;

    public int getThreadIndex() {
        return threadIndex;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    private int threadIndex = 0;

    private JSch jsch;
    private Session session;
    private String msg;


    public SshTry(String host, String userName, String password) {
        this.host = host;
        this.userName = userName;
        passwordDic = new ArrayList<String>();
        passwordDic.add(password);
        init();
    }

    public SshTry(String host, String userName, ArrayList<String> passwordDic) {
        this.host = host;
        this.userName = userName;
        this.passwordDic = passwordDic;
        init();
    }

    private void init() {
        this.port = 22;
    }

    @Override
    public void run() {
        for (String password : passwordDic) {

            startTry:
            if (tryOnce(password)) {
                msg = "host:" + host + "\tuser:" + userName + "\tpass:" + password + "\tsuccess!";
                System.out.println(msg);
                break startTry;
            } else {
                msg = "T" + threadIndex + "-pass:" + password + "\tfailed!";
                System.out.println(msg);
//                FileRecord.fileRecord.record(msg);
            }
        }
    }

    public boolean tryOnce(String password) {
        isPass = false;
        jsch = new JSch();
        try {
            session = jsch.getSession(this.userName, this.host, this.port);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties,禁用HostKeyCheck

            session.connect();
            session.disconnect();
            isPass = true;
        } catch (JSchException e) {
            isPass = false;
        }
        return isPass;
    }
}
