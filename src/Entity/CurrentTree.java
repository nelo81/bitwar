package Entity;

import Expection.CompileException;
import Expection.RunningException;

import Compiler.TokenList;
import Runtime.GlobalValue;

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
        if(!GlobalValue.contain("current"))
            throw new RunningException("'current' is not defined");
        return (Integer) GlobalValue.getGlobalVal("current");
    }
}
