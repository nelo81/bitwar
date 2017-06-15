package Entity;

import Expection.CompileException;
import Compiler.TokenList;
import Expection.RunningException;

import java.util.Map;

import static Compiler.TokenJudge.isId;
import static Compiler.TokenJudge.isNum;

@SuppressWarnings("all")
public class Leaf implements Tree{
    private String value;
    private String condition;

    public Leaf(){setValue(null);}

    public Leaf(String value) throws CompileException{
        setValue(value);
        grow(null);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void grow(TokenList tokens) throws CompileException {
        if(isId(getValue())) setCondition("id");
        else if(isNum(getValue())) setCondition("num");
    }

    @Override
    public Integer run(Map<String, Integer> localVal) throws RunningException {
        switch (getCondition()){
            case "id":
                if(localVal.containsKey(getValue())) return localVal.get(getValue());
                else throw new RunningException("id '"+getValue()+"is not defined");
            case "num":
                return Integer.parseInt(getValue());
            default: throw new RunningException("this leaf can't run");
        }
    }
}
