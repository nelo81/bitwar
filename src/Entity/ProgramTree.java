package Entity;

import Expection.CompileException;
import Util.TokenList;

import static Util.TokenJudge.isId;

public class ProgramTree implements Tree {
    private Leaf name;
    private StmtListTree stmtList;

    public ProgramTree() {
        setValue("program");
    }

    public Leaf getName() {
        return name;
    }

    public void setName(Leaf name) {
        this.name = name;
    }

    public StmtListTree getStmtList() {
        return stmtList;
    }

    public void setStmtList(StmtListTree stmtList) {
        this.stmtList = stmtList;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException{
        if(!tokens.read().equals("func")) throw new CompileException("not start with func");
        String value = tokens.read();
        if(isId(value)){
            setName(new Leaf(value));
        }
        StmtListTree tree = new StmtListTree();
        tree.grow(tokens);
        setStmtList(tree);
        if(!tokens.read().equals("endf")) throw new CompileException("function is not finished by endf");
    }

    @Override
    public int run() {
        return 0;
    }
}
