package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Compiler.Parser;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isId;

@SuppressWarnings("all")
public class AssignTree implements Tree{
    private Leaf id;
    private ExpTree exp;

    public AssignTree(){}

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(isId(tokens.watch())) id = new Leaf(tokens.read());
        else throw new CompileException("assign-stmt is not started with id");
        if(!tokens.read().equals("is"))
            throw new CompileException("assign-stmt format error");
        exp = new ExpTree();
        exp.grow(tokens);

    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        localVal.put(id.getValue(), exp.run(localVal));
        return null;
    }

    @Override
    public void print(int deep) {
        id.print(deep);
        Parser.printWord(deep,"is");
        exp.print(deep);
    }
}