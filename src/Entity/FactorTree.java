package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isConst;
import static Util.TokenJudge.isId;
import static Util.TokenJudge.isNum;

@SuppressWarnings("all")
public class FactorTree implements Tree{
    private ExpTree exp;
    private Leaf value;
    private RandomTree random;
    private ConstTree con;

    public FactorTree(){
        setValue("factor");
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
    public int run() {
        return 0;
    }
}
