package vocabulary;

import java.io.*;
import java.util.*;


public class Vocabulary {

   public static Map<String, Integer> vocabulary = new TreeMap<>();
    
    public static void addToCollection(Map<String, Integer> map, String[] words)
    {
        for(String str : words){
            if(!map.containsKey(str)){
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        map.remove("");
    }
    
    public static String[] makeStringValid (String str)
    {
        String[] string = str.toLowerCase().replaceAll("\\s*(\\?|\"|«|»|—\\s|\\s|,|:|!|\\.|\\(|\\))\\s*", " ")
                .split("\\s+");
        return string;
    }
    
    public static void printCollection(Map<String,Integer> map){
        try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("result.txt"), "UTF-8")))
        {   
            for(Map.Entry<String, Integer> item : map.entrySet()){
                pw.write(item.getKey() + " - " + item.getValue() + "\n");
            }
            pw.flush();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        File dir = new File("text");
        if (dir.isDirectory()){
            for (File item : dir.listFiles()){
                try(BufferedReader br = new BufferedReader(new FileReader(item)))
                {
                    String str;
                    String validString[];
                    while((str = br.readLine())!=null){
                        validString = makeStringValid(str);
                        addToCollection(vocabulary, validString);
                        printCollection(vocabulary);
                    }
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        
     
    }
    
}
