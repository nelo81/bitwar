package Entry;

import Expection.CmdException;
import Expection.CompileException;
import Expection.RunningException;
import Runtime.*;
import Compiler.Parser;

import java.util.*;

@SuppressWarnings("all")
public class Cmd {
    private List<String> cmd;

    private static Map<String, String> help;
    private static Runner runner;

    static {
        runner = new Runner();
        help = new HashMap<>();
        help.put("load","[target directory]|[]\n" +
                "      load all strategies from the target directory(default 'strategy')\n");
        help.put("ls","print all programs' name which had been loaded\n");
        help.put("show","[target strategy]\n" +
                "      print the grammar tree of target strategy\n");
        help.put("run","[target strategy]\n" +
                "     run the target strategy\n");
        help.put("battle","[strategy1,strategy2,round]|[round]\n" +
                "        start a battle between two strategies (default all strategies)\n");
    }

    public Cmd(String cmd){
        String[] list = cmd.split("\\s");
        this.cmd = new ArrayList<String>(Arrays.asList(list));
    }

    public void execute() throws CompileException, CmdException, RunningException{
        switch (cmd.get(0)){
            case "help":help();break;
            case "load":load();break;
            case "ls":ls();break;
            case "show":show();break;
            case "run":run();break;
            case "battle":battle();break;
            case "exit":break;
            case "":break;
            default:
                throw new CmdException("'"+cmd.get(0)+"' is not a command");
        }
    }

    private void help() throws CmdException{
        if(cmd.size()!=1) throw new CmdException("wrong args for help command");
        System.out.println("usage:");
        for(Map.Entry<String,String> entry:help.entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

    private void load() throws CompileException, CmdException{
        if(cmd.size()==1) {
            if(runner.load("strategy"))
                System.out.println("successfully load strategies from default 'strategy'");
            else throw new CmdException("default directory 'strategy' is not exist");
        }
        else if(cmd.size()==2) {
            if(runner.load(cmd.get(1)))
                System.out.println("successfully load strategies from '"+cmd.get(1)+"'");
            else throw new CmdException("directory '"+cmd.get(1)+"' is not exist");
        }
        else throw new CmdException("wrong args for load command");
    }

    private void ls() throws CmdException{
        if(cmd.size()!=1) throw new CmdException("wrong args for ls command");
        GlobalValue.printAllPrograms();
    }

    private void show() throws CmdException{
        if(cmd.size()!=2) throw new CmdException("wrong args for show command");
        Parser.printTree(cmd.get(1),GlobalValue.getProgram(cmd.get(1)));
    }

    private void run() throws CmdException, RunningException{
        if(cmd.size()!=2) throw new CmdException("wrong args for run command");
        String target = cmd.get(1);
        if(!GlobalValue.hasProgram(target)) throw new CmdException("'"+target+"' is not loaded");
        System.out.println("result: "+runner.run(target));
    }

    private void battle() throws CmdException, RunningException{
        if(cmd.size()==4){
            String target1 = cmd.get(1);
            String target2 = cmd.get(2);
            String round = cmd.get(3);
            if(!round.matches("[0-9]+"))
                throw new CmdException("'"+round+"' is not a number over zero");
            int round2 = Integer.parseInt(round);
            if(round2<=0)
                throw new CmdException("'"+round2+"' is not a number over zero");
            if(!GlobalValue.hasProgram(target1))
                throw new CmdException("'"+target1+"' is not loaded");
            if(!GlobalValue.hasProgram(target2))
                throw new CmdException("'"+target2+"' is not loaded");
            BattleEntry.battle(runner,target1,target2,round2,false);
        }
        else if(cmd.size()==2){
            if(GlobalValue.getPrograms().size()<2)
                throw new CmdException("there are no enough programs to battle");
            String round = cmd.get(1);
            if(!round.matches("[0-9]+"))
                throw new CmdException("'"+round+"' is not a number over zero");
            int round2 = Integer.parseInt(round);
            if(round2<=0)
                throw new CmdException("'"+round2+"' is not a number over zero");
            BattleEntry.battle(runner,round2);
        }
        else throw new CmdException("wrong args for battle command");
    }
}
