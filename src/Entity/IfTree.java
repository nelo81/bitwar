package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

@SuppressWarnings("all")
public class IfTree implements Tree{
    private ExpTree ifPart;
    private StmtListTree thenPart;
    private StmtListTree elsePart;
    private String condition;

    public IfTree(){}

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        if(ifPart.run(localVal)!=0){
            return thenPart.run(localVal);
        }
        else if(getCondition().equals("else")){
            return elsePart.run(localVal);
        }
        return null;
    }
}
