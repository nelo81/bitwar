package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

@SuppressWarnings("all")
public class ReturnTree implements Tree{
    private ExpTree exp;

    public ReturnTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("return"))
            throw new CompileException("return-stmt is not started by 'return'");
        exp = new ExpTree();
        exp.grow(tokens);
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        return exp.run(localVal);
    }
}
