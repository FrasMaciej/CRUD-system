package file;

import model.Record;

import java.io.*;
import java.util.TreeMap;

public class FileManager {
    private final String filePath = "dictionary.data";

    public void serializeDictionary(TreeMap<String, Record> dictionary) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dictionary);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public TreeMap<String, Record> deserializeDictionary() throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(filePath);
        File file = new File(filePath);
        if(file.length() != 0){
            ObjectInputStream ois = new ObjectInputStream(fis);
            TreeMap<String, Record> dictionary;
            dictionary = (TreeMap<String, Record>)ois.readObject();
            ois.close();
            return dictionary;
        }
        else return new TreeMap<>();
    }

}
