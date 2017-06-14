package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class RandomTree implements Tree{
    private ExpTree exp;

    public RandomTree(){
        setValue("random");
    }

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
    public int run() {
        return 0;
    }
}
