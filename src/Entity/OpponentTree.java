package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;
import Runtime.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class OpponentTree implements Tree{
    private ExpTree exp;

    public OpponentTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("opponent"))
            throw new CompileException("opponent is not started with 'random'");
        if(!tokens.read().equals("("))
            throw new CompileException("opponent format error");
        exp = new ExpTree();
        exp.grow(tokens);
        if(!tokens.read().equals(")"))
            throw new CompileException("opponent format error");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        localVal.put("current", Program.OPPONENT_CURRENT);
        int arg = exp.run(localVal);
        int index = localVal.get("my");
        if(index==Program.FIRST_RUN){
            if (arg > ((List) GlobalValue.getGlobalVal("history2")).size()){
                throw new RunningException("current index out of array 'history2'");
            }
            return (Integer) ((List) GlobalValue.getGlobalVal("history2")).get(arg-1);
        }
        if(index==Program.SECOND_RUN){
            if (arg > ((List) GlobalValue.getGlobalVal("history1")).size()){
                throw new RunningException("current index out of array 'history1'");
            }
            return (Integer) ((List) GlobalValue.getGlobalVal("history1")).get(arg-1);
        }
        else {
            throw new RunningException("my-stmt cannot run in single mode");
        }
    }
}
