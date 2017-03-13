package pers.badger.util;

/**
 * Created by badger on 2017/3/12.
 */
public class Host {
    private String hostName;
    private String ipStr;
    private int[] ipAddress;

    private int A;

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }

    public int getD() {
        return D;
    }

    private int B;
    private int C;
    private int D;


    public Host(String hostName) {
        this.hostName = hostName;
        this.ipStr = new Lookup().getIP(hostName);
        getIpAddress();
    }

    private void getIpAddress() {
        this.ipAddress = new int[4];
        String[] ipStrSplit = ipStr.split("\\.");
        if (ipStrSplit.length == 4) {
            for (int i = 0; i < ipStrSplit.length; i++) {
                ipAddress[i] = Integer.parseInt(ipStrSplit[i]);
            }
        }
        this.A = ipAddress[0];
        this.B = ipAddress[1];
        this.C = ipAddress[2];
        this.D = ipAddress[3];
    }
}
