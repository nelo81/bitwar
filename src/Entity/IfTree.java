package Entity;

import Expection.CompileException;
import Util.TokenList;

public class IfTree implements Tree{
    private ExpTree ifPart;
    private StmtListTree thenPart;
    private StmtListTree elsePart;

    public ExpTree getIfPart() {
        return ifPart;
    }

    public void setIfPart(ExpTree ifPart) {
        this.ifPart = ifPart;
    }

    public StmtListTree getThenPart() {
        return thenPart;
    }

    public void setThenPart(StmtListTree thenPart) {
        this.thenPart = thenPart;
    }

    public StmtListTree getElsePart() {
        return elsePart;
    }

    public void setElsePart(StmtListTree elsePart) {
        this.elsePart = elsePart;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("if"))
            throw new CompileException("if-stmt is not started with if");
        ExpTree exp = new ExpTree();
        exp.grow(tokens);
        setIfPart(exp);
        if(tokens.read().equals("then")){
            StmtListTree thenTree = new StmtListTree();
            thenTree.grow(tokens);
            setThenPart(thenTree);
            setCondition("then");
            if(tokens.read().equals("else")){
                StmtListTree elseTree = new StmtListTree();
                elseTree.grow(tokens);
                setThenPart(elseTree);
                setCondition("else");
            }
        }
        if(!tokens.read().equals("endi"))
            throw new CompileException("if-stmt is not finished by endi");
    }

    @Override
    public int run() {
        return 0;
    }
}
