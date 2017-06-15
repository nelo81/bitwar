package Runtime;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public Integer run(String name, int order) throws RunningException{
        return GlobalValue.getProgram(name).run(order);
    }

    public Integer run(String name) throws RunningException{
        return GlobalValue.getProgram(name).run();
    }

    public void battle(String s1, String s2, int round) throws RunningException{
        Integer result1;
        Integer result2;
        int point1 = 0;
        int point2 = 0;
        GlobalValue.clearGlobalVal();
        List<Integer> history1 = new ArrayList<>();
        history1.add(0);
        List<Integer> history2 = new ArrayList<>();
        history2.add(0);
        GlobalValue.setGlobalVal("history1", history1);
        GlobalValue.setGlobalVal("history2", history2);
        for(int i=1;i<round;i++){
            result1 = run(s1, Program.FIRST_RUN);
            history1.add(result1);
            result2 = run(s2, Program.SECOND_RUN);
            history2.add(result2);
            if(result1==null||result2==null)
                throw new RunningException("no return for battle");
            if(result1==0 && result2==0){
                point1 += 1;
                point2 += 1;
            }
            if(result1==1 && result2==0){
                point2 += 5;
            }
            if(result1==0 && result2==1){
                point1 += 5;
            }
            if(result1==1 && result2==1){
                point1 += 3;
                point2 += 3;
            }
            System.out.println(result1+"   "+result2);
        }
        System.out.println();
        System.out.println(point1+"   "+point2);
    }

    public static void main(String args[]){
        Runner runner = new Runner();
        try{
            runner.battle("t3.txt","t2.txt",10);
        }catch (RunningException re){
            System.err.println(re);
        }
    }
}
