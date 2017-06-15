package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isLogic;

@SuppressWarnings("all")
public class ExpTree implements Tree{
    private LogicExpTree left;
    private Leaf logic;
    private LogicExpTree right;
    private String condition;

    public ExpTree(){}

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        if(getCondition().equals("hasLogic")){
            if(logic.getValue().equals("and"))
                if(left.run(localVal)!=0 && right.run(localVal)!=0) return 1;
                else return 0;
            else if (logic.getValue().equals("or"))
                if(left.run(localVal)!=0 || right.run(localVal)!=0) return 1;
                else return 0;
            else throw new RunningException("exp grammar error");
        }
        else {
            return left.run(localVal);
        }
    }
}
