package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isComp;

@SuppressWarnings("all")
public class LogicExpTree implements Tree{
    private SimpleExpTree left;
    private Leaf comp;
    private SimpleExpTree right;

    public LogicExpTree(){
        setValue("logic-exp");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        left = new SimpleExpTree();
        left.grow(tokens);
        if(isComp(tokens.watch())){
            comp = new Leaf(tokens.read());
            setCondition("hasComp");
            right = new SimpleExpTree();
            right.grow(tokens);
        }
        else setCondition("noComp");
    }

    @Override
    public int run() {
        return 0;
    }
}
