package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class IfTree implements Tree{
    private ExpTree ifPart;
    private StmtListTree thenPart;
    private StmtListTree elsePart;

    public IfTree(){
        setValue("if-stmt");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(!tokens.read().equals("if"))
            throw new CompileException("if-stmt is not started with if");
        ifPart = new ExpTree();
        ifPart.grow(tokens);
        if(tokens.read().equals("then")){
            thenPart = new StmtListTree();
            thenPart.grow(tokens);
            setCondition("then");
            if(tokens.watch().equals("else")){
                tokens.read();
                elsePart = new StmtListTree();
                elsePart.grow(tokens);
                setCondition("else");
            }
        }
        else throw new CompileException("without then part in if-stmt");
        if(!tokens.read().equals("endi"))
            throw new CompileException("if-stmt is not finished by endi");
    }

    @Override
    public int run() {
        return 0;
    }
}
