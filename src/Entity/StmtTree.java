package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isId;

@SuppressWarnings("all")
public class StmtTree implements Tree{
    private IfTree ifTree;
    private WhileTree whileTree;
    private AssignTree assignTree;
    private ReturnTree returnTree;
    private StmtTree nextStmt;

    public StmtTree(){
        setValue("stmt");
    }

    public void setNextStmt(StmtTree nextStmt) {
        this.nextStmt = nextStmt;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        String token = tokens.watch();
        if(token.equals("if")){
            ifTree = new IfTree();
            ifTree.grow(tokens);
            setCondition("if");
        }
        else if(token.equals("while")){
            whileTree = new WhileTree();
            whileTree.grow(tokens);
            setCondition("while");
        }
        else if(token.equals("return")){
            returnTree = new ReturnTree();
            returnTree.grow(tokens);
            setCondition("return");
        }
        else if(isId(token)){
            assignTree = new AssignTree();
            assignTree.grow(tokens);
            setCondition("assign");
        }
        else {
            setCondition("null");
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
