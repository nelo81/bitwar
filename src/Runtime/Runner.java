package Runtime;

import java.io.File;

import Compiler.Parser;
import Expection.CompileException;
import Expection.RunningException;

@SuppressWarnings("all")
public class Runner {
    public Runner(){}

    public boolean load(String path) throws CompileException{
        File dir = new File(path);
        if(dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            for(File file:files){
                if(file.getName().endsWith(".txt")){
                    GlobalValue.inputProgram(file.getName(), Parser.parse(file.getPath()));
                }
            }
            return true;
        }
        else return false;
    }

    public Integer run(String name, int order) throws RunningException{
        return GlobalValue.getProgram(name).run(order);
    }

    public Integer run(String name) throws RunningException{
        return GlobalValue.getProgram(name).run();
    }

    public static void main(String args[]){
        Runner runner = new Runner();
        try{
            runner.load("strategy");
            System.out.println(runner.run("t1.txt"));
        }catch (CompileException ce){
            System.err.println(ce);
        }catch (RunningException re){
            System.err.println(re);
        }
    }
}
