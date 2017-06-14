package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isId;

@SuppressWarnings("all")
public class AssignTree implements Tree{
    private Leaf id;
    private ExpTree exp;

    public AssignTree(){
        setValue("assign-stmt");
    }

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
    public int run() {
        return 0;
    }
}