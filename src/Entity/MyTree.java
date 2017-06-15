package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

@SuppressWarnings("all")
public class MyTree implements Tree{
    private ExpTree exp;

    public MyTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("my"))
            throw new CompileException("my-stat is not started with 'my'");
        if(!tokens.read().equals("("))
            throw new CompileException("my format error");
        exp = new ExpTree();
        exp.grow(tokens);
        if(!tokens.read().equals(")"))
            throw new CompileException("my format error");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        return 0;
    }
}
