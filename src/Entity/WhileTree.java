package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class WhileTree implements Tree{
    private ExpTree exp;
    private StmtListTree stmts;

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("while"))
            throw new CompileException("while-stmt is not started with 'while'");
        exp = new ExpTree();
        exp.grow(tokens);
        if(!tokens.read().equals("do"))
            throw new CompileException("without do part in while-stmt");
        stmts = new StmtListTree();
        stmts.grow(tokens);
        if(!tokens.read().equals("endw"))
            throw new CompileException("while-stmt is not finished by 'endw'");
    }

    @Override
    public int run() {
        return 0;
    }
}
