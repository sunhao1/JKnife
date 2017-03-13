package pers.badger.FileIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by badger on 2017/3/12.
 */
public class FileRecord {

    public static FileRecord fileRecord = new FileRecord("dic/ResultOut");

    private String filePath;

    FileOutputStream outputStream;


    public FileRecord(String content, String filePath) {
        this.filePath = filePath;

        try {
            outputStream = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        record(content);
    }

    public FileRecord(String filePath) {
        this.filePath = filePath;

        try {
            outputStream = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void record(String contentStr) {
        try {
            outputStream.write((contentStr + "\n").getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
