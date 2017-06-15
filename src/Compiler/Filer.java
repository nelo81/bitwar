package Compiler;

import java.io.*;

@SuppressWarnings("all")
public class Filer {
    public static String readFile(String path){
        String result = "";
        try{
            File file = new File(path);
            InputStreamReader in = new InputStreamReader(new FileInputStream(file));
            BufferedReader buf = new BufferedReader(in);
            String line = null;
            while ((line = buf.readLine()) != null){
                result += line + " ";
            }
            in.close();
        }catch (Exception e){
            System.err.println(e);
        }
        return result;
    }

    public static void main(String args[]) {
        System.out.println(readFile("strategy/t1.txt"));
    }
}
