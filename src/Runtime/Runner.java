package Runtime;

import java.io.File;

import Compiler.Parser;
import Expection.CompileException;
import Expection.RunningException;

@SuppressWarnings("all")
public class Runner {

    public Runner(String path){
        File dir = new File(path);
        if(dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            for(File file:files){
                if(file.getName().endsWith(".txt")){
                    try{
                        GlobalValue.inputProgram(file.getName(), Parser.parse(file.getPath()));
                    }catch (CompileException ce){
                        System.err.println(file.getName() + ": " + ce);
                    }
                }
            }
        }
        else System.err.println("directory is not exist");
    }

    public Runner(){
        this("strategy");
    }

    public void printAllPrograms(){
        for(String key:GlobalValue.getPrograms().keySet()){
            System.out.println(key);
        }
    }

    public Integer run(String name) throws RunningException{
        return GlobalValue.getProgram(name).run();
    }

    public void battle(){

    }

    public static void main(String args[]){
        Runner runner = new Runner();
        try{
            System.out.println(runner.run("t2.txt"));
        }catch (RunningException re){
            System.err.println(re);
        }

    }
}
