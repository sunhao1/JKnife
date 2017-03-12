package pers.badger.FileIO;

import java.util.ArrayList;

/**
 * 根据读取的字典文件，解析端口列表与范围
 * Created by badger on 2017/3/12.
 */
public class LoadPortDic extends LoadDicFile {

    //定义分隔符
    private String regexStr = "-";

    //单独端口号为默认列表，端口范围为自定义列表
    private ArrayList<Integer> defaultPortList;
    private ArrayList<Integer[]> customPortList;

    private Integer[] customPortRange;

    public LoadPortDic(String filePath) {
        super(filePath);
        loadPortList();
    }

    public ArrayList<Integer> getDefaultPortList() {
        return defaultPortList;
    }

    public ArrayList<Integer[]> getCustomPortList() {
        return customPortList;
    }

    private void loadPortList() {
        defaultPortList = new ArrayList<Integer>();
        customPortList = new ArrayList<Integer[]>();
        for (String line : this.fileLines) {

            // 包含分隔符的时候，则根据分隔符进行范围分割，只有第一个分割有效，无分割，则为单独端口号
            if (line.contains(regexStr)) {
                customPortRange = new Integer[2];
                customPortRange[0] = Integer.parseInt(line.split(regexStr)[0]);
                customPortRange[1] = Integer.parseInt(line.split(regexStr)[1]);

                // 起止端口号不能颠倒否则无效
                if (customPortRange[0] < customPortRange[1])
                    customPortList.add(customPortRange);
            } else {
                defaultPortList.add(Integer.parseInt(line));
            }
        }
    }

}
