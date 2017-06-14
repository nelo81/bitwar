package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class OpponentTree implements Tree{
    private ExpTree exp;

    public OpponentTree(){
        setValue("opponent");
    }

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
    public int run() {
        return 0;
    }
}
