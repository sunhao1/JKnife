package pers.badger.FileIO;

import java.io.*;
import java.util.ArrayList;

/**
 * 根据路径读取字典文件
 * Created by badger on 2017/3/12.
 */
public class LoadDicFile {
    private String filePath;

    private StringBuffer sb;
    private BufferedReader br;
    private FileReader reader;

    private String lineStr;

    /* 读取结果 */
    protected ArrayList<String> fileLines;

    public LoadDicFile(String filePath) {
        this.filePath = filePath;
        loadFile();
    }


    public ArrayList<String> getFileLines() {
        return fileLines;
    }


    //在字典中读取各个元素，忽略空行，返回行列表
    private void loadFile() {
        fileLines = new ArrayList<String>();
        try {
            reader = new FileReader(filePath);
            br = new BufferedReader(reader);
            sb = new StringBuffer("");
            while ((lineStr = br.readLine()) != null) {
                if (lineStr.length() > 0 && !lineStr.contains("#")) {
                    fileLines.add(lineStr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
