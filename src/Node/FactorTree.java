package Node;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isConst;
import static Compiler.TokenJudge.isId;
import static Compiler.TokenJudge.isNum;

@SuppressWarnings("all")
public class FactorTree implements Tree{
    private ExpTree exp;
    private Leaf value;
    private RandomTree random;
    private ConstTree con;
    private String condition;

    public FactorTree(){}

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(tokens.watch().equals("(")){
            setCondition("exp");
            tokens.read();
            exp = new ExpTree();
            exp.grow(tokens);
            if(!tokens.read().equals(")"))
                throw new CompileException("factor format error");
        }
        else if(isId(tokens.watch())||isNum(tokens.watch())){
            setCondition("value");
            value = new Leaf(tokens.read());
        }
        else if (tokens.watch().equals("random")){
            setCondition("random");
            random = new RandomTree();
            random.grow(tokens);
        }
        else if (isConst(tokens.watch())){
            setCondition("const");
            con = new ConstTree();
            con.grow(tokens);
        }
        else {
            throw new CompileException("'"+tokens.watch()+"' is not a factor");
        }
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        switch (getCondition()){
            case "exp": return exp.run(localVal);
            case "value": return value.run(localVal);
            case "random": return random.run(localVal);
            case "const": return con.run(localVal);
            default:
                throw new RunningException("factor grammar error");
        }
    }

    @Override
    public void print(int deep) {
        switch (getCondition()){
            case "exp": exp.print(deep+1);break;
            case "value": value.print(deep);break;
            case "random": random.print(deep);break;
            case "const": con.print(deep);break;
            default:;
        }
    }
}
