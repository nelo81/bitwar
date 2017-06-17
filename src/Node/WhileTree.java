package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Compiler.Parser;
import Expection.RunningException;

import java.util.Map;

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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        while (exp.run(localVal)!=0){
            Integer val = stmts.run(localVal);
            if(val!=null) return val;
        }
        return null;
    }

    @Override
    public void print(int deep) {
        Parser.printWord(deep,"while");
        exp.print(deep+1);
        Parser.printWord(deep+1,"do");
        stmts.print(deep+2);
        Parser.printWord(deep+1,"-do");
        Parser.printWord(deep,"endw");
    }
}
