package Entity;

import Expection.CompileException;
import Expection.RunningException;

import Compiler.TokenList;
import Runtime.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class CurrentTree implements Tree{
    public CurrentTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("current"))
            throw new CompileException("current format error");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        int current = localVal.get("current");
        int order = localVal.get("my");
        if (order==Program.FIRST_RUN){
            if (current==Program.MY_CURRENT){
                return ((List)GlobalValue.getGlobalVal("history1")).size();
            }
            if (current==Program.OPPONENT_CURRENT){
                return ((List)GlobalValue.getGlobalVal("history2")).size();
            }
            throw new RunningException("current val is invaild");
        }
        if (order==Program.SECOND_RUN){
            if (current==Program.MY_CURRENT){
                return ((List)GlobalValue.getGlobalVal("history2")).size();
            }
            if (current==Program.OPPONENT_CURRENT){
                return ((List)GlobalValue.getGlobalVal("history1")).size();
            }
            throw new RunningException("current val is invaild");
        }
        else {
            throw new RunningException("my-stmt cannot run in single mode");
        }
    }
}
