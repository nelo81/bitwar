package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class ReturnTree implements Tree{
    private ExpTree exp;

    public ReturnTree(){
        setValue("return-stmt");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("return"))
            throw new CompileException("return-stmt is not started by 'return'");
        exp = new ExpTree();
        exp.grow(tokens);
    }

    @Override
    public int run() {
        return 0;
    }
}
