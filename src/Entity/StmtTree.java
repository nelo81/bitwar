package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isId;

@SuppressWarnings("all")
public class StmtTree implements Tree{
    private IfTree ifTree;
    private WhileTree whileTree;
    private AssignTree assignTree;
    private ReturnTree returnTree;
    private StmtTree nextStmt;
    private String condition;

    public StmtTree(){
        nextStmt = null;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean hasNext(){
        return nextStmt != null;
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
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        Integer val = null;
        switch (getCondition()){
            case "if":
                val = ifTree.run(localVal);
                if(val==null) return hasNext()?nextStmt.run(localVal):null;
                else return val;
            case "while":
                val = whileTree.run(localVal);
                if(val==null) return hasNext()?nextStmt.run(localVal):null;
                else return val;
            case "assign":
                assignTree.run(localVal);
                return hasNext()?nextStmt.run(localVal):null;
            case "return": return returnTree.run(localVal);
            default:
                throw new RunningException("stmt grammar error");
        }
    }
}
