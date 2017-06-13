package Entity;

import Expection.CompileException;
import Util.TokenList;

import java.util.ArrayList;
import java.util.List;

import static Util.TokenJudge.isId;

public class ProgramTree implements Tree {
    private Node name;
    private Node func;

    public ProgramTree() {
        root.setValue("program");
    }

    public Node getFunc() {
        return func;
    }

    public void setFunc() {
    }

    @Override
    public void grow(TokenList tokens) throws CompileException{
        if(!tokens.read().equals("func")) throw new CompileException("not start with func");
        if(isId(tokens.read())){
        }
    }

    @Override
    public int run() {
        return 0;
    }
}
