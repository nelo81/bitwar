package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

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
        return 0;
    }
}
