package model;

import file.FileManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;

public class Data implements Serializable {
    private static final long serialVersionUID = 13L;

    private static TreeMap<String, Record> dictionary = new TreeMap<>();

    private static FileManager fileManager;

    static {
        try {
            fileManager = new FileManager();
            dictionary = fileManager.deserializeDictionary();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void addRecord(Record record){
        dictionary.put(record.getName(), record);
    }

    public static void setDictionary(TreeMap<String, Record> readedDictionary) {
        dictionary = readedDictionary;
    }

    public static TreeMap<String, Record> getDictionary() {
        return dictionary;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

}
