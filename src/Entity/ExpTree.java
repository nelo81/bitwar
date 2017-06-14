package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isLogic;

@SuppressWarnings("all")
public class ExpTree implements Tree{
    private LogicExpTree left;
    private Leaf logic;
    private LogicExpTree right;

    public ExpTree(){
        setValue("exp");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        left = new LogicExpTree();
        left.grow(tokens);
        if(isLogic(tokens.watch())) {
            logic = new Leaf(tokens.read());
            setCondition("hasLogic");
            right = new LogicExpTree();
            right.grow(tokens);
        }
        else setCondition("noLogic");
    }

    @Override
    public int run() {
        return 0;
    }
}
