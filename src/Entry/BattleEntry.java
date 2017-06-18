package Entry;

import Expection.CompileException;
import Expection.RunningException;
import Runtime.*;

import java.util.*;

@SuppressWarnings("all")
public class BattleEntry {
    private static Map<String, Integer> pointCounter = new HashMap<>();

    public static void battle(Runner runner, int round) throws RunningException{
        for(String key: GlobalValue.getPrograms().keySet()){
            pointCounter.put(key, 0);
        }
        System.out.println("\nevery battle result:");
        for(String key1: GlobalValue.getPrograms().keySet()){
            for (String key2: GlobalValue.getPrograms().keySet()){
                if(!key1.equals(key2)){
                    battle(runner, key1, key2, round, true);
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(pointCounter.entrySet());
        list.sort((e1,e2)->e2.getValue()-e1.getValue());
        System.out.println("\nfinal battle result:");
        list.forEach(System.out::println);
    }

    public static void battle(Runner runner, String s1, String s2, int round, boolean record) throws RunningException {
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
        if(!record)System.out.println("\nresult:\t\t" + s1 + "\t" + s2);
        for(int i=0;i<round;i++){
            result1 = runner.run(s1, Program.FIRST_RUN);
            history1.add(result1);
            result2 = runner.run(s2, Program.SECOND_RUN);
            history2.add(result2);
            if(result1==null) throw new RunningException("first-run program has no return");
            if(result2==null) throw new RunningException("second-run program has no return");
            if(result1!=1 && result1!=0)
                throw new RunningException("the first-run program's return value is invaild for battle" +
                        "(it can only be 0 or 1)");
            if(result2!=1 && result2!=0)
                throw new RunningException("the second-run program's return value is invaild for battle" +
                        "(it can only be 0 or 1)");
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
            if(!record)System.out.println("round "+ (i+1) + ":\t" + result1 + "\t" + result2);
        }
        if(record){
            System.out.println(s1+" vs "+s2+": "+point1+"\t"+point2);
            pointCounter.put(s1, pointCounter.get(s1) + point1);
            pointCounter.put(s2, pointCounter.get(s2) + point2);
        }
        else System.out.println("\nfinal:\t" + s1 + "=" + point1 + "   " + s2 + "=" + point2);
    }

    public static void main(String args[]){
        try {
            Runner runner = new Runner();
            runner.load("strategy");
//            battle(new Runner(),"t3.txt","t4.txt", 10, false);
            battle(new Runner(), 200);
        }catch (CompileException ce){
            System.err.println(ce);
        }catch (RunningException re){
            System.err.println(re);
        }
    }
}
