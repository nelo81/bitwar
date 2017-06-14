package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.ArrayList;
import java.util.List;

import static Util.TokenJudge.isAddop;

@SuppressWarnings("all")
public class SimpleExpTree implements Tree{
    private List<TermTree> terms;
    private List<String> addops;

    public SimpleExpTree(){
        terms = new ArrayList<>();
        addops = new ArrayList<>();
        setValue("simple-exp");
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
    public int run() {
        return 0;
    }
}
