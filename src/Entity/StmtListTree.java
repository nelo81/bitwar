package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class StmtListTree implements Tree{
    private StmtTree firstStmt;
    private StmtTree cursor;

    public StmtListTree(){
        setValue("stmt-list");
        firstStmt = null;
    }

    public void addStmt(StmtTree tree){
        if(firstStmt == null) firstStmt = tree;
        else cursor.setNextStmt(tree);
        cursor = tree;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        while (true){
            StmtTree tree = new StmtTree();
            tree.grow(tokens);
            if(tree.getCondition().equals("null")) break;
            else addStmt(tree);
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
