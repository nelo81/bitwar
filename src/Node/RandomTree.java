package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Compiler.Parser;
import Expection.RunningException;

import java.util.Map;
import java.util.Random;

@SuppressWarnings("all")
public class RandomTree implements Tree{
    private ExpTree exp;

    public RandomTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("random"))
            throw new CompileException("random-stat is not started with 'random'");
        if(!tokens.read().equals("("))
            throw new CompileException("random format error");
        exp = new ExpTree();
        exp.grow(tokens);
        if(!tokens.read().equals(")"))
            throw new CompileException("random format error");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        int max = exp.run(localVal);
        Random r = new Random();
        return r.nextInt(max);
    }

    @Override
    public void print(int deep) {
        Parser.printWord(deep,"random");
        exp.print(deep+1);
        Parser.printWord(deep,"-random");
    }
}
