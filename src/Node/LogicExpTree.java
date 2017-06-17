package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isComp;

@SuppressWarnings("all")
public class LogicExpTree implements Tree{
    private SimpleExpTree left;
    private Leaf comp;
    private SimpleExpTree right;
    private String condition;

    public LogicExpTree(){}

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        if(getCondition().equals("hasComp")){
            if(comp.getValue().equals("<")){
                if(left.run(localVal)<right.run(localVal))
                    return 1;
                else return 0;
            }
            if(comp.getValue().equals(">")){
                if(left.run(localVal)>right.run(localVal))
                    return 1;
                else return 0;
            }
            if(comp.getValue().equals("=")){
                if(left.run(localVal)==right.run(localVal))
                    return 1;
                else return 0;
            }
        }
        if(getCondition().equals("noComp")){
            return left.run(localVal);
        }
        else throw new RunningException("logic-exp grammar error");
    }

    @Override
    public void print(int deep) {
        left.print(deep);
        if(getCondition().equals("hasComp")){
            comp.print(deep);
            right.print(deep);
        }
    }
}
