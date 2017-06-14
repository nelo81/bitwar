package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class ConstTree implements Tree{
    private Leaf current;
    private MyTree my;
    private OpponentTree opponent;

    public ConstTree(){
        setValue("const");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(tokens.watch().equals("current")){
            setCondition("current");
            current = new Leaf(tokens.read());
        }
        else if(tokens.watch().equals("my")){
            setCondition("my");
            my = new MyTree();
            my.grow(tokens);
        }
        else if(tokens.watch().equals("opponent")){
            setCondition("opponent");
            opponent = new OpponentTree();
            opponent.grow(tokens);
        }
        else {
            throw new CompileException("'"+tokens.watch()+"' is not a const value");
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
