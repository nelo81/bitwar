package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Compiler.TokenJudge.isAddop;

@SuppressWarnings("all")
public class SimpleExpTree implements Tree{
    private List<TermTree> terms;
    private List<String> addops;

    public SimpleExpTree(){
        terms = new ArrayList<>();
        addops = new ArrayList<>();
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        TermTree tree = new TermTree();
        tree.grow(tokens);
        terms.add(tree);
        while (isAddop(tokens.watch())){
            addops.add(tokens.read());
            tree = new TermTree();
            tree.grow(tokens);
            terms.add(tree);
        }
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        int result = terms.get(0).run(localVal);
        for(int i=0;i<addops.size();i++){
            if(addops.get(i).equals("+"))
                result += terms.get(i+1).run(localVal);
            else if(addops.get(i).equals("-"))
                result -= terms.get(i+1).run(localVal);
            else throw new RunningException("simple-exp grammar error");
        }
        return result;
    }
}
