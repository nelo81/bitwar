package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isId;

public class StmtTree implements Tree{
    private IfTree ifTree;
    private WhileTree whileTree;
    private AssignTree assignTree;
    private ReturnTree returnTree;

    public StmtTree(){
        setValue("stmt");
    }

    public IfTree getIfTree() {
        return ifTree;
    }

    public void setIfTree(IfTree ifTree) {
        this.ifTree = ifTree;
    }

    public WhileTree getWhileTree() {
        return whileTree;
    }

    public void setWhileTree(WhileTree whileTree) {
        this.whileTree = whileTree;
    }

    public AssignTree getAssignTree() {
        return assignTree;
    }

    public void setAssignTree(AssignTree assignTree) {
        this.assignTree = assignTree;
    }

    public ReturnTree getReturnTree() {
        return returnTree;
    }

    public void setReturnTree(ReturnTree returnTree) {
        this.returnTree = returnTree;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        String token = tokens.watch();
        if(token.equals("if")){
            IfTree tree = new IfTree();
            tree.grow(tokens);
            setIfTree(tree);
            setCondition("if");
        }
        else if(token.equals("while")){
            WhileTree tree = new WhileTree();
            tree.grow(tokens);
            setWhileTree(tree);
            setCondition("while");
        }
        else if(token.equals("return")){
            ReturnTree tree = new ReturnTree();
            tree.grow(tokens);
            setReturnTree(tree);
            setCondition("return");
        }
        else if(isId(token)){
            AssignTree tree = new AssignTree();
            tree.grow(tokens);
            setAssignTree(tree);
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
