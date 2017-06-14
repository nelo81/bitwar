package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.ArrayList;
import java.util.List;

import static Util.TokenJudge.isMulop;

@SuppressWarnings("all")
public class TermTree implements Tree{
    private List<FactorTree> factors;
    private List<String> mulops;

    public TermTree(){
        factors = new ArrayList<>();
        mulops = new ArrayList<>();
        setValue("term");
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        FactorTree tree = new FactorTree();
        tree.grow(tokens);
        factors.add(tree);
        while (isMulop(tokens.watch())){
            mulops.add(tokens.read());
            tree = new FactorTree();
            tree.grow(tokens);
            factors.add(tree);
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
