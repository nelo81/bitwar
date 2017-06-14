package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.ArrayList;
import java.util.List;

public class StmtListTree implements Tree{
    private List<StmtTree> stmtTrees;

    public StmtListTree(){
        setValue("stmt-list");
        stmtTrees = new ArrayList<>();
    }

    public void addStmtTree(StmtTree tree){
        stmtTrees.add(tree);
    }

    public List<StmtTree> getStmtTrees() {
        return stmtTrees;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        while (true){
            StmtTree tree = new StmtTree();
            tree.grow(tokens);
            if(tree.getCondition().equals("null")) break;
            else addStmtTree(tree);
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
