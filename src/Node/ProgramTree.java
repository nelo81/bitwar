package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Compiler.Parser;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isId;

@SuppressWarnings("all")
public class ProgramTree implements Tree {
    private Leaf name;
    private StmtListTree stmtList;

    public ProgramTree() {}

    @Override
    public void grow(TokenList tokens) throws CompileException{
        if(!tokens.read().equals("func")) throw new CompileException("not start with 'func'");
        String value = tokens.read();
        if(isId(value)) name = new Leaf(value);
        else throw new CompileException("function name is not an id");
        stmtList = new StmtListTree();
        stmtList.grow(tokens);
        if(!tokens.read().equals("endf")) throw new CompileException("function is not finished by 'endf'");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        return stmtList.run(localVal);
    }

    @Override
    public void print(int deep) {
        Parser.printWord(deep, "func " + name.getValue());
        stmtList.print(deep+1);
        Parser.printWord(deep, "endf");
    }
}
