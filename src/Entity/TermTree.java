package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Compiler.TokenJudge.isMulop;

@SuppressWarnings("all")
public class TermTree implements Tree{
    private List<FactorTree> factors;
    private List<String> mulops;

    public TermTree(){
        factors = new ArrayList<>();
        mulops = new ArrayList<>();
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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        int result = factors.get(0).run(localVal);
        for(int i=0;i<mulops.size();i++){
            if(mulops.get(i).equals("*"))
                result *= factors.get(i+1).run(localVal);
            else if(mulops.get(i).equals("/"))
                result /= factors.get(i+1).run(localVal);
            else throw new RunningException("term grammar error");
        }
        return result;
    }
}
