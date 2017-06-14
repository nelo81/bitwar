package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isLogic;

public class ExpTree implements Tree{
    private LogicExpTree left;
    private Leaf logic;
    private LogicExpTree right;

    public ExpTree(){}

    public LogicExpTree getLeft() {
        return left;
    }

    public void setLeft(LogicExpTree left) {
        this.left = left;
    }

    public Leaf getLogic() {
        return logic;
    }

    public void setLogic(Leaf logic) {
        this.logic = logic;
    }

    public LogicExpTree getRight() {
        return right;
    }

    public void setRight(LogicExpTree right) {
        this.right = right;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        LogicExpTree leftTree = new LogicExpTree();
        leftTree.grow(tokens);
        setLeft(leftTree);
        String value = tokens.read();
        if(isLogic(value)) {
            setLogic(new Leaf(value));
            setCondition("hasLogic");
            LogicExpTree rightTree = new LogicExpTree();
            rightTree.grow(tokens);
            setRight(rightTree);
        }
        else setCondition("noLogic");
    }

    @Override
    public int run() {
        return 0;
    }
}
