package Entity;

import Expection.CompileException;
import Util.TokenList;

@SuppressWarnings("all")
public class Leaf implements Tree{
    public Leaf(){setValue(null);}

    public Leaf(String value){
        setValue(value);
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {

    }

    @Override
    public int run() {
        return 0;
    }
}
